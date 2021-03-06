package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("Group name"));
        }
    }

    @Test
    public void testModifyGroup56() {
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).
                withName("group11").withHeader("header11").withFooter("footer11");
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(modifiedGroup).WithAdded(group)));
    }













    @Test
    public void testModifyGroupSet() {

        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();

        GroupData group = new GroupData().withId(modifiedGroup.getId()).
                withName("group11").withHeader("header11").withFooter("footer11");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        assertEquals(after.size(), before.size());

        // Старый список перед сравнением надо модифицировать -- удалить элемент по индексу
        before.remove(modifiedGroup);
        // А вместо него добавим тот, который должен появиться после модификации = new GroupData("group11", "header11", "footer11")
        before.add(group);
        assertEquals(before, after);
    }

    @Test (enabled = false)
    public void testModifyGroupSortedLists() {

        List<GroupData> before = app.group().list();
        // Берём размер списка и вычитаем из него единицу и помещаем в переменную index
        int index = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(index).getId()).
                withName("group11").withHeader("header11").withFooter("footer11");
        app.group().modifyByIndex(index, group);
        List<GroupData> after = app.group().list();
        assertEquals(after.size(), before.size());

        // Старый список перед сравнением надо модифицировать -- удалить элемент по индексу
        before.remove(index);
        // А вместо него добавим тот, который должен появиться после модификации = new GroupData("group11", "header11", "footer11")
        before.add(group);

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        assertEquals(before, after);

    }

    @Test
    public void testModifyGroup() {

        app.goTo().groupPage();

        if (!app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("Group name"));
        }
        List<GroupData> before = app.group().list();
        // Берём размер списка и вычитаем из него единицу
        app.group().selectGroup(before.size() - 1);
        app.group().initGroupModification();
        // Сделаем локальную переменную, что бы два раза не прописывать список
        // И добавим в неё id группы, которую мы модифицировали: before.get(before.size() -1).getId()
        GroupData group = new GroupData().withId(before.get(before.size() - 1).getId()).
                withName("group11").withHeader("header11").withFooter("footer11");

        app.group().fillGroupForm(group);
        app.group().updateGroup();
        app.group().returnToGroupPage();
        List<GroupData> after = app.group().list();
        assertEquals(after.size(), before.size());

        // Старый список перед сравнением надо модифицировать -- удалить последний элемент
        before.remove(before.size() - 1);
        // А вместо него добавим тот, который должен появиться после модификации = new GroupData("group11", "header11", "footer11")
        before.add(group);

        assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}


package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupModificationTests extends TestBase{

  @Test
  public void testModifyGroupSortedLists() {

    app.getNavigationHelper().goToGroupPage();

    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "header1", "footer1"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    // Берём размер списка и вычитаем из него единицу
    app.getGroupHelper().selectGroup(before.size() -1);
    app.getGroupHelper().initGroupModification();
    // Сделаем локальную переменную, что бы два раза не прописывать список
    // И добавим в неё id группы, которую мы модифицировали: before.get(before.size() -1).getId()
    GroupData group = new GroupData(before.get(before.size() -1).getId(), "group11", "header11", "footer11");

    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().updateGroup();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    // Старый список перед сравнением надо модифицировать -- удалить последний элемент
    before.remove(before.size() -1);
    // А вместо него добавим тот, который должен появиться после модификации = new GroupData("group11", "header11", "footer11")
    before.add(group);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);

  }

  @Test
  public void testModifyGroup() {

    app.getNavigationHelper().goToGroupPage();

    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "header1", "footer1"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    // Берём размер списка и вычитаем из него единицу
    app.getGroupHelper().selectGroup(before.size() -1);
    app.getGroupHelper().initGroupModification();
    // Сделаем локальную переменную, что бы два раза не прописывать список
    // И добавим в неё id группы, которую мы модифицировали: before.get(before.size() -1).getId()
    GroupData group = new GroupData(before.get(before.size() -1).getId(), "group11", "header11", "footer11");

    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().updateGroup();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    // Старый список перед сравнением надо модифицировать -- удалить последний элемент
    before.remove(before.size() -1);
    // А вместо него добавим тот, который должен появиться после модификации = new GroupData("group11", "header11", "footer11")
    before.add(group);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }
}


package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().
                    withName("Group name").withHeader("Header").withFooter("Footer"));
        }
    }

    @Test
    public void testDeleteGroupSet56() {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.group().all();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedGroup)));
    }

    @Test
    public void testDeleteGroupSet() {
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().all();
        assertEquals(after.size(), before.size() - 1);
        // Удаляем из списка before группу с тем же идексом, что и у удалённой = before.size - 1
        before.remove(deletedGroup);
        // Добавили в GroupData метод equals и удалили из кода луп, так как этот метод сам умеет его делать
        assertEquals(before, after);
    }

    @Test
    public void testDeleteGroup() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        assertEquals(after.size(), before.size() - 1);
        // Удаляем из списка before группу с тем же идексом, что и у удалённой = before.size - 1
        before.remove(index);
        // Добавили в GroupData метод equals и удалили из кода луп, так как этот метод сам умеет его делать
        assertEquals(before, after);
    }


}
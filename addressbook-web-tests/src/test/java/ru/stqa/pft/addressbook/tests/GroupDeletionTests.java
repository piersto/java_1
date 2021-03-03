package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
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
    public void testDeleteGroup56() {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size() -1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }

    @Test
    public void testDeleteGroupSet() {
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Set<GroupData> after = app.group().all();
        // Удаляем из списка before группу с тем же идексом, что и у удалённой = before.size - 1
        before.remove(deletedGroup);
        // Добавили в GroupData метод equals и удалили из кода луп, так как этот метод сам умеет его делать
        assertEquals(before, after);
    }

    @Test
    public void testDeleteGroupSortedList() {
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
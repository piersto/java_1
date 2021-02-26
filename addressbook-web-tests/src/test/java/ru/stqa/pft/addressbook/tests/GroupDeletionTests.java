package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void insurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1", "header1", "footer1"));
        }
    }

    @Test
    public void testDeleteGroup() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);
        // Удаляем из списка before группу с тем же идексом, что и у удалённой = before.size - 1
        before.remove(index);
        // Добавили в GroupData метод equals и удалили из кода луп, так как этот метод сам умеет его делать
        Assert.assertEquals(before, after);
    }


}
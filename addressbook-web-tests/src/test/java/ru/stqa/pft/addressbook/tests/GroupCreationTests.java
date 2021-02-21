package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("test1", "header1", "footer1"));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        // Сравниваем пока только размеры списков:
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}

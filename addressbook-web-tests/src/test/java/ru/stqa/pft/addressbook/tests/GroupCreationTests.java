package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.goToGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("group1", "header1", "footer"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }
}

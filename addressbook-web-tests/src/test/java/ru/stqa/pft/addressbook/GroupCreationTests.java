package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("group1", "header1", "footer"));
        submitGroupCreation();
        returnToGroupPage();
        wd.findElement(By.linkText("Logout")).click();
    }
}

package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class ModifyGroupTests extends TestBase{
  private WebDriver wd;

  @Test
  public void testModifyGroup() throws Exception {

    goToGroupPage();
    selectGroup();
    initGroupModification();
    fillGroupForm(new GroupData("group11", "header11", "footer11"));
    updateGroup();
  }
}


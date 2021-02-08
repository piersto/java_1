package ru.stqa.pft.addressbook;

import org.testng.annotations.*;


public class ModifyGroupTests extends TestBase{

  @Test
  public void testModifyGroup() throws Exception {

    goToGroupPage();
    selectGroup();
    initGroupModification();
    fillGroupForm(new GroupData("group11", "header11", "footer11"));
    updateGroup();
  }
}


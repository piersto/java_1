package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;


public class ModifyGroupTests extends TestBase{

  @Test
  public void testModifyGroup() throws Exception {

    app.goToGroupPage();
    app.selectGroup();
    app.initGroupModification();
    app.fillGroupForm(new GroupData("group11", "header11", "footer11"));
    app.updateGroup();
  }
}


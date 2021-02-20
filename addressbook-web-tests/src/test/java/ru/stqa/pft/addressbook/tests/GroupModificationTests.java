package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupModificationTests extends TestBase{

  @Test
  public void testModifyGroup() {

    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();

    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "header1", "footer1"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("group11", "header11", "footer11"));
    app.getGroupHelper().updateGroup();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }
}


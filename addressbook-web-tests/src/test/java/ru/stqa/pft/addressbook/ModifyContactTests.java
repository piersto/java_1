package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class ModifyContactTests extends TestBase {


  @Test
  public void testModifyContact() throws Exception {
    selectContact();
    initContactModification();

    returnToGroupPage();
    // wd.findElement(By.linkText("home page")).click();
  }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ModifyContactTests extends TestBase {


    @Test
    public void testModifyContact() throws Exception {
        app.selectContact();
        app.initContactModification();
        app.fillContactForm(new ContactData("Masha prosto Kvasha", "Ivanovna", "Petrova", "QA Analyst", "CBC", "Montreal", "555-666-7777", "mpetrova@gmail.com"));
        app.submitContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}

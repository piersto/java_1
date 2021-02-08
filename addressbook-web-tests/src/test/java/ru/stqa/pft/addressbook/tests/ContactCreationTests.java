package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testAddContact() throws Exception {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Masha", "Ivanovna", "Petrova", "QA Analyst", "CBC", "Montreal", "555-666-7777", "mpetrova@gmail.com"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToHomePage();
    }
}

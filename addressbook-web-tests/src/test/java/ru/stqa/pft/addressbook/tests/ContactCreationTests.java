package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testAddContact() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().createContact(new ContactData("Masha", "Ivanovna",
                "Petrova", "QA Analyst", "CBC", "Montreal",
                "555-666-7777", "mpetrova@gmail.com", "[none]"), true);
        app.getNavigationHelper().returnToHomePage();
    }
}

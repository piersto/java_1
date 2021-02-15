package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactModificationTests extends TestBase {


    @Test
    public void testModifyContact() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("Masha",
                    "Ivanovna", "Petrova", "QA Analyst",
                    "CBC", "Montreal", "111-222-3333",
                    "mpetrova@gmail.com", "[none]"), true);
            app.getNavigationHelper().returnToHomePage();
        }

            app.getContactHelper().selectContact();
            app.getContactHelper().initContactModification();
            app.getContactHelper().fillContactForm(new ContactData("Masha prosto Kvasha", "Ivanovna",
                    "Dubrova", "QA Analyst", "CBC", "Montreal",
                    "444-555-666", "mpetrova@YAHOO.com", "[none]"), false);
            app.getContactHelper().submitContactModification();
            app.getNavigationHelper().returnToHomePage();
        }

}
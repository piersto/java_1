package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("Masha",
                    "Ivanovna", "Petrova", "QA Analyst",
                    "CBC", "Montreal", "555-666-7777",
                    "mpetrova@gmail.com", "[none]"), true);
            app.getNavigationHelper().returnToHomePage();
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before -1);
        app.getContactHelper().deleteSelectedContacts();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before -1);
    }
}


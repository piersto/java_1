package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactModificationTests extends TestBase {


    @Test
    public void testModifyContact() {
        if (! app.getContactHelper().isThereAContact())
        {
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("Masha",
                    "Ivanovna", "Petrova", "QA Analyst",
                    "CBC", "Montreal", "111-222-3333",
                    "mpetrova@gmail.com", "[none]"), true);
            app.getNavigationHelper().returnToHomePage();
        }
            List<ContactData> before = app.getContactHelper().getContactList();
            app.getContactHelper().selectContact(before.size() - 1);
            app.getContactHelper().initContactModification();
            app.getContactHelper().fillContactForm(new ContactData("Masha prosto Kvasha", "Ivanovna",
                    "Dubrova", "QA Analyst", "CBC", "Montreal",
                    "444-555-666", "mpetrova@YAHOO.com", "[none]"), false);
            app.getContactHelper().submitContactModification();
            app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        }

}
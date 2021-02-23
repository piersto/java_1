package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
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
            ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Masha prosto Kvasha", "Ivanovna",
                    "Dubrova", "QA Analyst", "CBC", "Montreal",
                    "444-555-666", "mpetrova@YAHOO.com", "[none]");
            app.getContactHelper().fillContactForm(contact, false);
            app.getContactHelper().submitContactModification();
            app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        }

}
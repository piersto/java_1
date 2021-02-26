package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testAddContactSorted() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();

        ContactData contact = new ContactData("Masha", "Ivanovna",
                "Petrova", "QA Analyst", "CBC", "Montreal",
                "555-666-7777", "mpetrova@gmail.com", "[none]");

        app.getContactHelper().createContact(contact, true);
        app.goTo().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);

        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testAddContact() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();

        ContactData contact = new ContactData("Masha", "Ivanovna",
                "Petrova", "QA Analyst", "CBC", "Montreal",
                "555-666-7777", "mpetrova@gmail.com", "[none]");

        app.getContactHelper().createContact(contact, true);
        app.goTo().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);
/*
        int max = 0;
        for (ContactData c : after) {
            if (c.getId() > max) {
                max = c.getId();
            }
        }
*/
        // Находим max Id, и добавляем в contact
        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}

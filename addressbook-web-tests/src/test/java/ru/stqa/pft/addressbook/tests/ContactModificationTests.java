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
                    null, "Petrova", null,
                    null, null, null,
                    null, "[none]"), true);
            app.getNavigationHelper().returnToHomePage();
        }
            List<ContactData> before = app.getContactHelper().getContactList();
            // app.getContactHelper().selectContact(before.size() - 1);
            app.getContactHelper().initContactModification(before.size() - 1);
            // Делаем локальную переменную, так как использовать будем не один раз
            ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Maria",
                    null,  "Ivanova", null, null, null,
                    null, null, "[none]");

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
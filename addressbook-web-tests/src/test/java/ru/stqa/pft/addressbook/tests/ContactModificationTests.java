package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;


public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (! app.getContactHelper().isThereAContact())
        {
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("Masha",
                    null, "Petrova", null,
                    null, null, null,
                    null, "[none]"), true);
            app.goTo().returnToHomePage();
        }
    }

    @Test
    public void testModifyContactSorted() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        // Делаем локальную переменную, так как использовать будем не один раз
        ContactData contact = new ContactData(before.get(index).getId(),"Maria",
                null,  "Ivanova", null, null, null,
                null, null, "[none]");
        // app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().modifyContact(index, contact);
        app.goTo().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        // Сравниваем упорядоченные списки
        Assert.assertEquals(before, after);
    }



    @Test
    public void testModifyContact() {
        if (! app.getContactHelper().isThereAContact())
        {
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("Masha",
                    null, "Petrova", null,
                    null, null, null,
                    null, "[none]"), true);
            app.goTo().returnToHomePage();
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
            app.goTo().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        // Преобразовываем списки в о множества (set)
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        }

}
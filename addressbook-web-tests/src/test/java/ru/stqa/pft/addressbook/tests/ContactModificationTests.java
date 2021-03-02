package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;


public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().list().size() == 0)
        {
            app.contact().initContactCreation();
            app.contact().create(new ContactData().
                    withFirstname("Masha").withMiddlename("Ivanovna").withLastname("Petrova").
                    withTitle("QA Analyst").withCompany("CBC").withAddress("Montreal").withHomephone("555-666-7777").
                    withEmail("mpetrova@gmail.com").withGroup("[none]"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testModifyContactSorted56() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();

        // Делаем локальную переменную, так как использовать будем не один раз
        ContactData contact = new ContactData().withId(modifiedContact.getId()).
                withFirstname("Masha").withMiddlename("Ivanovna").withLastname("Petrova").
                withTitle("QA Analyst").withCompany("CBC").withAddress("Montreal").
                withHomephone("555-666-7777").withEmail("mpetrova@gmail.com").withGroup("[none]");
        // app.getContactHelper().selectContact(before.size() - 1);
        app.contact().modifyById(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        // Сравниваем упорядоченные sets
        Assert.assertEquals(before, after);
    }

    @Test
    public void testModifyContactSorted() {
        List<ContactData> before = app.contact().list();
        // Номер (index) контакта, которого будем удалять
        int index = before.size() - 1;
        // Делаем локальную переменную, так как использовать будем не один раз
        ContactData contact = new ContactData().
                withFirstname("Masha").withMiddlename("Ivanovna").withLastname("Petrova").
                withTitle("QA Analyst").withCompany("CBC").withAddress("Montreal").
                withHomephone("555-666-7777").withEmail("mpetrova@gmail.com").withGroup("[none]");
        // app.getContactHelper().selectContact(before.size() - 1);
        app.contact().modify(index, contact);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
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
        if (! app.contact().isThereAContact())
        {
            app.contact().initContactCreation();
            app.contact().create(new ContactData().
                    withFirstname("Masha").withMiddlename("Ivanovna").withLastname("Petrova").
                    withTitle("QA Analyst").withCompany("CBC").withAddress("Montreal").withHomephone("555-666-7777").
                    withEmail("mpetrova@gmail.com").withGroup("[none]"), true);
            app.goTo().homePage();
        }
            List<ContactData> before = app.contact().list();
            // app.getContactHelper().selectContact(before.size() - 1);
            app.contact().initContactModification(before.size() - 1);
            // Делаем локальную переменную, так как использовать будем не один раз
            ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId()).
                                            withFirstname("Masha").
                                         withMiddlename("Ivanovna").
                                    withLastname("Petrova").
                                 withTitle("QA Analyst").
                             withCompany("CBC").
                         withAddress("Montreal").
                     withHomephone("555-666-7777").
                 withEmail("mpetrova@gmail.com").
               withGroup("[none]");

            app.contact().fillContactForm(contact, false);
            app.contact().submitContactModification();
            app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        // Преобразовываем списки в о множества (set)
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        }

}
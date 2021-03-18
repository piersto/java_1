package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().initContactCreation();
            app.contact().create(new ContactData()
                    .withFirstname("Masha")
                    .withLastname("Petrova")
                    .withAddress("Montreal")
                    .withEmail("1@1.com")
                    .withEmail2("2@1.com")
                    .withEmail3("3@1.com")
                    .withHomephone("11111111")
                    .withMobilephone("222222")
                    .withWorkphone("3333333")
                    .withGroup("[none]"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testModifyContact74() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();

        // Делаем локальную переменную, так как использовать будем не один раз
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname("Masha")
                .withLastname("Petrova")
                .withAddress("Montreal")
                .withEmail("1@1.com")
                .withEmail2("2@1.com")
                .withEmail3("3@1.com")
                .withHomephone("11111111")
                .withMobilephone("222222")
                .withWorkphone("3333333")
                .withGroup("[none]");
        app.contact().modifyById(contact);
        app.goTo().homePage();

        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }












    @Test (enabled = false)
    public void testModifyContactSortedList() {
        List<ContactData> before = app.contact().list();
        // Номер (index) контакта, которого будем удалять
        int index = before.size() - 1;
        // Делаем локальную переменную, так как использовать будем не один раз
        ContactData contact = new ContactData().
                withFirstname("Masha").withMiddlename("Ivanovna").withLastname("Petrova").
                withTitle("QA Analyst").withCompany("CBC").withAddress("Montreal").
                withHomephone("555-666-7777").withEmail("mpetrova@gmail.com").withGroup("[none]");
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

    @Test(enabled = false)
    public void testModifyContact() {
        if (!app.contact().isThereAContact()) {
            app.contact().initContactCreation();
            app.contact().create(new ContactData().
                    withFirstname("Masha").withMiddlename("Ivanovna").withLastname("Petrova").
                    withTitle("QA Analyst").withCompany("CBC").withAddress("Montreal").withHomephone("555-666-7777").
                    withEmail("mpetrova@gmail.com").withGroup("[none]"), true);
            app.goTo().homePage();
        }
        List<ContactData> before = app.contact().list();
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
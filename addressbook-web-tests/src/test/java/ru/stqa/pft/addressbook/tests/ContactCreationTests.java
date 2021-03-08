package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test()
    public void testCreateContactWithPhoto() {
        Contacts before = app.contact().all();
        app.contact().initContactCreation();
        File photo = new File("src/test/resources/2.png");
        ContactData contact = new ContactData()
                .withFirstname("Masha")
                .withLastname("Petrova")
                .withGroup("[none]")
                .withPhoto(photo);

        app.contact().create(contact, true);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.
                withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

    @Test(enabled = false)
    public void testAddContact56() {
        Contacts before = app.contact().all();
        app.contact().initContactCreation();
        File photo = new File("src/test/resources/2.png");
        ContactData contact = new ContactData()
                .withFirstname("Masha")
                .withLastname("Petrova")
                .withGroup("[none]");

        app.contact().create(contact, true);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.
                withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }


    @Test(enabled = false)
    public void currentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath() + " -- this is my print");
        File photo = new File("src/test/resources/2.png");
        // Выводим на консоль полный путь к файлу
        System.out.println(photo.getAbsolutePath());
        // Проверяем, что файл существует
        System.out.println(photo.exists());
    }


    @Test(enabled = false)
    public void testAddContactSortedList() {
        List<ContactData> before = app.contact().list();
        app.contact().initContactCreation();

        ContactData contact = new ContactData().
                withFirstname("Masha").withMiddlename("Ivanovna").withLastname("Petrova").
                withTitle("QA Analyst").withCompany("CBC").withAddress("Montreal").withHomephone("555-666-7777").
                withEmail("mpetrova@gmail.com").withGroup("[none]");

        app.contact().create(contact, true);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


    @Test
    public void testAddContact() {
        List<ContactData> before = app.contact().list();
        app.contact().initContactCreation();

        ContactData contact = new ContactData().
                withFirstname("Masha").withMiddlename("Ivanovna").withLastname("Petrova").
                withTitle("QA Analyst").withCompany("CBC").withAddress("Montreal").withHomephone("555-666-7777").
                withEmail("mpetrova@gmail.com").withGroup("[none]");
        ;

        app.contact().create(contact, true);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);
/*
        int max = 0;
        for (ContactData c : after) {
            if (c.getId() > max) {
                max = c.getId();
            }
        }
*/
        // Находим max Id, и добавляем в contact
        contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}

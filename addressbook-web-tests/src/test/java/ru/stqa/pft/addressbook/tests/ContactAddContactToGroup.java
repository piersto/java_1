package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactAddContactToGroup extends TestBas{

    @Test
    public void testCreateContact74() {
        Contacts before = app.db().contacts();
        app.contact().initContactCreation();
        ContactData contact = new ContactData()
                .withFirstname("Masha")
                .withLastname("Petrova")
                .withGroup("[none]")
                .withAddress("Montreal")
                .withEmail("1@1.com")
                .withEmail2("2@1.com")
                .withEmail3("3@1.com")
                .withHomephone("11111111")
                .withMobilephone("222222")
                .withWorkphone("3333333");

        app.contact().create(contact, true);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.
                withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }
}



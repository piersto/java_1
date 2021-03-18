package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddContactToGroup extends TestBase{

    @Test
    public void testAdddContactToGroup() {

        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData contact = new ContactData()
                .withFirstname("Masha")
                .withLastname("Petrova")
                .inGroup(groups.iterator().next())
                .withAddress("Montreal")
                .withEmail("1@1.com")
                .withEmail2("2@1.com")
                .withEmail3("3@1.com")
                .withHomephone("11111111")
                .withMobilephone("222222")
                .withWorkphone("3333333");

        app.contact().initContactCreation();
        app.contact().create(contact, true);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.
                withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }
}



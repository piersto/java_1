package ru.stqa.pft.addressbook.tests;

import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddContactToGroupTests3 extends TestBase {
    private static SessionFactory sessionFactory;
    private ContactData modifiedContact;


    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().initContactCreation();
        modifiedContact = new ContactData()
                .withFirstname("Masha")
                .withLastname("Petrova");
        app.contact().create(modifiedContact, true);
        app.goTo().homePage();

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Group1"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactAddToGroup() {
        Contacts before = app.db().contacts();
        //ContactData modifiedContact = before.iterator().next();
        ContactData contact = modifiedContact;

        //ContactData contact = new ContactData().withId(modifiedContact.getId());
        app.goTo().homePage();
        // Select contact
        app.contact().selectContactById(modifiedContact.getId());
        // Click Add button
        app.contact().addContactToGroup();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(contact).withAdded(modifiedContact)));
    }
}

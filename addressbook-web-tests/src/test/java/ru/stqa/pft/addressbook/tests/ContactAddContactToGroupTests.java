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

public class ContactAddContactToGroupTests extends TestBase {
    private static SessionFactory sessionFactory;


    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
            app.contact().initContactCreation();
            app.contact().create(new ContactData()
                    .withFirstname("Masha")
                    .withLastname("Petrova"), true);
            app.goTo().homePage();
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Group1"));
            app.goTo().homePage();
        }

        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (int i = 0; i < contacts.size(); i++){

        }
    }

    @Test
    public void testContactAddToGroup2() {
    }













    @Test
    public void testContactAddToGroup() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId());
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



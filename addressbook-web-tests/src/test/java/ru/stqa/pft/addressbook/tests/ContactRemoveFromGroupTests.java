package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTests extends TestBase{

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
    }

    @Test
    public void testContactRemoveFromGroup() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        // Click details button by id
        app.contact().selectContactDetailsById(contact.getId());
        // Click Add button
        app.contact().deleteFromGroupById(contact);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}



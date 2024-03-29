package ru.stqa.pft.addressbook.tests;

import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemoveFromGroupTests4 extends TestBase {
    private static SessionFactory sessionFactory;
    private int contactId;
    private int groupId;


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

        for (ContactData contact : contacts) {
            contactId = contact.getId();
            Groups groupsInContact = contact.getGroups();
            for (GroupData group : groups) {
                groupId = group.getId();
                if (!groupsInContact.contains(group)) {
                    app.goTo().homePage();
                    app.contact().selectContactById(contactId);
                    app.contact().addContactToGroupWithSpecificId(String.valueOf(groupId));
                }
            }
        }
    }

    @Test
    public void testContactRemoveFromGroup4() {
        app.goTo().homePage();
        app.contact().filterContactsByGroup(String.valueOf(groupId));
        app.contact().selectContactById(contactId);
        app.contact().removeContactFromGroup();

        Contacts contacts = app.db().contactById(contactId);
        for (ContactData contact : contacts) {
            Groups groupsInContact = contact.getGroups();
            System.out.println("my groups in contact: "+ groupsInContact);
            Assert.assertFalse(groupsInContact.contains(String.valueOf(groupId)));
        }
    }
}

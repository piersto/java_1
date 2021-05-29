package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class ContactRemoveFromGroupTests5 extends TestBase {
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

        // Take contact from the list of contacts
        ContactData contact = contacts.iterator().next();
        // Get contacts id
        contactId = contact.getId();
        // Get groups for the contact
        Contacts contactGroups = app.db().contactById(contactId);
        // Get group from the list of groups
        GroupData group = groups.iterator().next();
        groupId = group.getId();
        // If contact's group isn't in the group


        // Add contact to group
    }


    @Test
    public void testContactRemoveFromGroup5() {
        app.goTo().homePage();
        app.contact().filterContactsByGroup(String.valueOf(groupId));
        app.contact().selectContactById(contactId);
        app.contact().removeContactFromGroup();

    }

}



















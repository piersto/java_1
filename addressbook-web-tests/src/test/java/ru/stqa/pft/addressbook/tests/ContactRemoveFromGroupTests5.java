package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
        Groups contactGroups = contact.getGroups();
        // Verify if contactGroups.size > 0
        if (contactGroups.size() == 0) {
            groupId = groups.iterator().next().getId();
            app.goTo().homePage();
            app.contact().selectContactById(contactId);
            app.contact().addContactToGroupWithSpecificId(String.valueOf(groupId));
        }
        else
        groupId = contactGroups.iterator().next().getId();
    }


    @Test
    public void testContactRemoveFromGroup5() {
        Contacts contacts = app.db().contactById(contactId);
        Groups contactGroupsBefore = contacts.iterator().next().getGroups();
        GroupData groupUnderTest = new GroupData();
        for (GroupData g : contactGroupsBefore) {
            int groupsId = g.getId();
            if (groupsId == groupId) {
                groupUnderTest = g;
            }
        }

        app.goTo().homePage();
        app.contact().filterContactsByGroup(String.valueOf(groupId));
        app.contact().selectContactById(contactId);
        app.contact().removeContactFromGroup();

        contacts = app.db().contactById(contactId);
        Groups contactGroupsAfter = contacts.iterator().next().getGroups();
        //contactGroupsAfter.add(groupUnderTest);
        contactGroupsBefore.remove(groupUnderTest);
        Assert.assertEquals(contactGroupsAfter, contactGroupsBefore);
    }
}



















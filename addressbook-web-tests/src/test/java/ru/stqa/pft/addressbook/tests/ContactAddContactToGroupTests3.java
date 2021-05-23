package ru.stqa.pft.addressbook.tests;

import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactAddContactToGroupTests3 extends TestBase {
    private static SessionFactory sessionFactory;
    private ContactData modifiedContact;
    private int maxId;


    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().initContactCreation();
        modifiedContact = new ContactData()
                .withFirstname("Masha")
                .withLastname("Zetrova");
        app.contact().create(modifiedContact, true);
        app.goTo().homePage();
        Contacts result = app.db().contacts();

        System.out.println(result);
        for ( ContactData contact : result) {
            if (contact.getId() > maxId) {
                maxId = contact.getId();
            }
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Group1"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactAddToGroup2() {
        app.goTo().homePage();
        app.contact().selectContactById(maxId);
        app.contact().addContactToGroup();

        Contacts modifiedContact = app.db().contactById(maxId);
        int sizeOfGroupsInContact = 0;
        for ( ContactData contact : modifiedContact ) {
            sizeOfGroupsInContact = contact.getGroups().size();
        }
        Assert.assertTrue(sizeOfGroupsInContact > 0);
        System.out.println(modifiedContact);
    }
    
}

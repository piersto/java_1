package ru.stqa.pft.addressbook.tests;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddContactToGroupTests extends TestBase{
    private static SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp2() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }
/*
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
    public void testContactAddToGroup2() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        // Get Contacts list
        List<ContactData> listOfContacts = session.createQuery
                ( "from ContactData where deprecated = '0000-00-00'" ).list();        // Get Groups list
        // Get Groups list
        List<GroupData> listOFGroups = session.createQuery( "from GroupData" ).list();
        // Iterate through listOfContacts to get groups in every contact
        for (ContactData contact : listOfContacts) {
            List<Integer> contactGroups = contact.getGroups();
            // Iterate through the group to verify if group in contactGroups
            for (GroupData group : listOFGroups) {
                if (group.contains(contactGroups);
                return;

            }
        }


        // Iterate Contacts to get Contact's groups
        // Iterate Groups to verify if group's id in Contact's groups Yes - pass, No - add


        //Contacts before = app.db().contacts();
        //ContactData modifiedContact = before.iterator().next();
        //ContactData contact = new ContactData().withId(modifiedContact.getId());

        /*
        app.goTo().homePage();
        // Select contact
        app.contact().selectContactById(modifiedContact.getId());
        // Click Add button
        app.contact().addContactToGroup();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(contact).withAdded(modifiedContact)));


    }
 */












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



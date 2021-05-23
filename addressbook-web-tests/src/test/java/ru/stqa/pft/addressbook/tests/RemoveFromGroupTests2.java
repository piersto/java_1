package ru.stqa.pft.addressbook.tests;

import org.hibernate.SessionFactory;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class RemoveFromGroupTests2 extends TestBase{

    private static SessionFactory sessionFactory;
    private ContactData modifiedContact;
    private int maxId;
    private int groupId;

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

        app.goTo().homePage();
        app.contact().selectContactById(maxId);
        app.contact().addContactToGroup();

        app.goTo().groupPage();
        // Get first group id
        groupId = app.group().getFirstGroupIdInTheDropDown();

    }

}

package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().initContactCreation();
            app.contact().create(new ContactData().
                    withFirstname("Masha").withMiddlename("Ivanovna").withLastname("Petrova").
                    withTitle("QA Analyst").withCompany("CBC").withAddress("Montreal").withHomephone("555-666-7777").
                    withEmail("mpetrova@gmail.com").withGroup("[none]"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion74() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }












    @Test (enabled = false)
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        assertEquals(after.size(), before.size() - 1);
        before.remove(index);
        assertEquals(before, after);
    }
}


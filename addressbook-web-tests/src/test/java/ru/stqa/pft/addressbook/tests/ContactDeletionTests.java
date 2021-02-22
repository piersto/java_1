package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("Masha",
                    "Ivanovna", "Petrova", "QA Analyst",
                    "CBC", "Montreal", "555-666-7777",
                    "mpetrova@gmail.com", "[none]"), true);
            app.getNavigationHelper().returnToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        // Так как before теперь список, а не int, то его длинну получаем через size()
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().deleteSelectedContacts();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() -1);
        before.remove(before.size() -1);
        Assert.assertEquals(before, after);
    }
}


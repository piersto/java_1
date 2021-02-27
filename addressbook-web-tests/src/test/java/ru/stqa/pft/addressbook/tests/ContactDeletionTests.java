package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (! app.getContactHelper().isThereAContact())
        {
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("Masha",
                    null, "Petrova", null,
                    null, null, null,
                    null, "[none]"), true);
            app.goTo().returnToHomePage();
        }
    }

    @Test
    public void testContactDeletion() {
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


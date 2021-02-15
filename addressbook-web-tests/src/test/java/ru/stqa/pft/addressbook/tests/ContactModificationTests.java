package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactModificationTests extends TestBase {


    @Test
    public void testModifyContact() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("Masha",
                    null, "Petrova", null,
                    null, "Montreal", null,
                    "mpetrova@gmail.com", "[none]"), true);
            app.getNavigationHelper().returnToHomePage();
        }

            app.getContactHelper().selectContact();
            app.getContactHelper().initContactModification();
            app.getContactHelper().fillContactForm(new ContactData("Masha prosto Kvasha", "Ivanovna",
                    "Petrova3", "QA Analyst", "CBC", "Montreal",
                    "555-666-7777", "mpetrova@gmail.com", "[none]"), false);
            //app.getNavigationHelper().returnToHomePage();
        }

}

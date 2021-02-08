package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


    @Test
    public void testAddContact() throws Exception {
        initContactCreation();
        fillContactForm(new ContactData("Masha", "Ivanovna", "Petrova", "QA Analyst", "CBC", "Montreal", "555-666-7777", "mpetrova@gmail.com"));
        submitContactCreation();
        returnToHomePage();
    }
}

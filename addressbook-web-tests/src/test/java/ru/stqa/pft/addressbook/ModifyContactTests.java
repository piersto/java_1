package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;


public class ModifyContactTests extends TestBase {


    @Test
    public void testModifyContact() throws Exception {
        selectContact();
        initContactModification();
        fillContactForm(new ContactData("Masha prosto Kvasha", "Ivanovna", "Petrova", "QA Analyst", "CBC", "Montreal", "555-666-7777", "mpetrova@gmail.com"));
        submitContactModification();
        returnToHomePage();
    }
}

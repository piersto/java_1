package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionTests() throws Exception {
        selectContact();
        deleteSelectedContacts();
    }
}


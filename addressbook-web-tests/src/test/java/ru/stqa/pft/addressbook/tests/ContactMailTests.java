package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().initContactCreation();
            app.contact().create(new ContactData().
                    withFirstname("Masha").
                    withLastname("Petrova").
                    withEmail("1@gmail.com").
                    withEmail2("2@gamil.com").
                    withEmail3("3@gmail.com").
                    withGroup("[none]"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactMails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));

    }

    private String mergeMails(ContactData contact) {
        return Arrays.asList(
                contact.getEmail(),
                contact.getEmail2().
                        concat(contact.getEmail3()))
                .stream().filter(s -> !s.equals(""))
                .map(ContactMailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String mail) {
        return mail.replaceAll("\\s", "");
    }
}


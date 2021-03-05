package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().initContactCreation();
            app.contact().create(new ContactData().
                    withFirstname("Masha").
                    withLastname("Petrova").
                    withAddress("11-2222, Depuis,Montreal, Quebec, H3S3H3, Canada")
                    .withGroup("[none]"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String address){
        return address.replaceAll("\\s", "");
    }

}

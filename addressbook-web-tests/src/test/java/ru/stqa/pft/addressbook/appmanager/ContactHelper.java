package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) { super(wd);}

    public void delete(int index) {
        selectContact(index);
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
        click(By.id("logo"));
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
        click(By.id("logo"));
    }

    public void deleteFromGroupById(ContactData contact) {
        selectContactDetailsById(contact.getId());
        clickOnGroupInContactDetails();
        selectContactById(contact.getId());
        clickRemoveFromGroup();
    }

    public void modifyById(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectFirstContact() {
        wd.findElement(By.name("selected[]")).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("tr[name='entry'] input[value ='" + id + "']")).click();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();

    }
    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href ='edit.php?id=%s']", id))).click();
    }

    public void selectContactDetailsById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href ='view.php?id=%s']", id))).click();
    }

    public void clickOnGroupInContactDetails() {
        click(By.cssSelector("#content > i > a"));
    }

    public void clickRemoveFromGroup() {
        click(By.cssSelector("[name='remove']"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());

        type(By.name("middlename"), contactData.getMiddlename());

        type(By.name("lastname"), contactData.getLastname());

        type(By.name("title"), contactData.getTitle());

        type(By.name("company"), contactData.getCompany());

        type(By.name("address"), contactData.getAddress());

        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("work"), contactData.getWorkphone());


        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());


        attach(By.name("photo"), contactData.getPhoto());

        // If we create contact:
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        // Извлекаем группу и берём у неё имя
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void addContactToGroup() {
        click(By.name("add"));
    }

    public void submitContactModification() { click(By.name("update")); }

    public void modify(int index, ContactData contact) {
        initContactModification(index);
        fillContactForm(contact, false);
        submitContactModification();
    }

    public void create(ContactData contact, boolean creation) {
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return (wd.findElements(By.name("selected[]")).size());

    }

    public List<ContactData> list() {
        // Создаём список который будем заполнять
        List<ContactData> contacts = new ArrayList<ContactData>();
        // Определяем строку в таблице из которой будем брать имя и фамилию
        List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
        // Теперь проходим по этим строкам в цикле и берём имя и фамилию
        for (WebElement row : rows) {
            int id = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(1) input")).
                                                                    getAttribute("value"));
            String lastname = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstname = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
    public Contacts all() {
        // Создаём список который будем заполнять
        Contacts contacts = new Contacts();
        // Определяем строку в таблице из которой будем брать имя и фамилию
        List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
        // Теперь проходим по этим строкам в цикле и берём имя и фамилию
        for (WebElement row : rows) {
            int id = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(1) input")).
                    getAttribute("value"));
            String lastname = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstname = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String allPhones = row.findElement(By.cssSelector("td:nth-child(6)")).getText();
            String allMails = row.findElement(By.cssSelector("td:nth-child(5)")).getText();
            String address = row.findElement(By.cssSelector("td:nth-child(4)")).getText();
            //String[] phones = allPhones.split("\n");
            contacts.add(new ContactData().withId(id).withFirstname(firstname).
                    withLastname(lastname).withAllPhones(allPhones).withAllMails(allMails).withAddress(address));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withHomephone(homephone)
                .withMobilephone(mobilephone)
                .withWorkphone(workphone)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3)
                .withAddress(address);
    }

    public void removeContactFromGroup() {
        click(By.name("remove"));
    }
}

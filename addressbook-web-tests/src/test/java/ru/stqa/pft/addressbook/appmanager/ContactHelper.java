package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void delete(int index) {
        selectContact(index);
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
        click(By.id("logo"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
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

        type(By.name("email"), contactData.getEmail());

        // If we create contact:
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }
        // Else- we modify contact so 'new_group' shouldn't be on the page and we assert it's not there.
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

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
    public Set<ContactData> all() {
        // Создаём список который будем заполнять
        Set<ContactData> contacts = new HashSet<ContactData>();
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
}

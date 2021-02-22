package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
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

    public void createContact(ContactData contact, boolean creation) {
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return (wd.findElements(By.name("selected[]")).size());

    }

    public List<ContactData> getContactList() {
        // Создаём список который будем заполнять
        List<ContactData> contacts = new ArrayList<ContactData>();
        // Определяем строку в таблице из которой будем брать имя и фамилию
        List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
        // Теперь проходим по этим строкам в цикле и берём имя и фамилию
        for (WebElement row : rows) {
            String lastname = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstname = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            // Создаём объект типа ContactData
            ContactData contact = new ContactData(lastname, null, firstname,null,
                    null, null, null, null, null);
            // И добавляем в этот объект текст, который прочитал в строках
            contacts.add(contact);
        }
        return contacts;
    }
}

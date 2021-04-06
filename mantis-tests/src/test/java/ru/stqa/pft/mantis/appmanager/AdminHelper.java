package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public AdminHelper(ApplicationManager app) {
     this.app = app;
     wd = app.getDriver();
    }

    public void openLoginPage() {
        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
    }

    public void login() {
        wd.findElement(By.id("username")).click();
        wd.findElement(By.id("username")).clear();
        wd.findElement(By.id("username")).sendKeys("administrator");
        wd.findElement(By.cssSelector("input[type='submit']")).click();
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys("root");
        wd.findElement(By.cssSelector("input[type='submit']")).click();
    }
    public void openMangeUsersPage() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }

    public void initPasswordChange(String user) {
        // Search for user piersto
        wd.findElement(By.id("search")).click();
        wd.findElement(By.id("search")).clear();
        wd.findElement(By.id("search")).sendKeys(user);
        // Click "Apply filter" button
        wd.findElement(By.cssSelector("input[type='submit']")).click();
        // Click on the first user in the list
        wd.findElement(By.cssSelector("tbody tr a")).click();
        String email = wd.findElement(By.id("email-field")).getAttribute("value");
        // Click 'Reset Password' button value="Reset Password"
        wd.findElement(By.cssSelector("fieldset > span > input")).click();
    }

    public void logout() {
        wd.get(app.getProperty("web.baseUrl") + "logout_page.php");
    }
}

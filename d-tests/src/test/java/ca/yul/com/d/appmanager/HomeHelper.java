package ca.yul.com.d.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeHelper{
    private final ApplicationManager app;
    private WebDriver wd;

    public HomeHelper(ApplicationManager app) {
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
        // Search for user
        wd.findElement(By.id("search")).click();
        wd.findElement(By.id("search")).clear();
        wd.findElement(By.id("search")).sendKeys(user);
        // Click "Apply filter" button
        wd.findElement(By.cssSelector("input[type='submit']")).click();
        // Click on the username in the list
        wd.findElement(By.linkText(user)).click();
        // Click 'Reset Password' button value="Reset Password"
        wd.findElement(By.cssSelector("fieldset > span > input")).click();
    }
    public void logout() {
        wd.get(app.getProperty("web.baseUrl") + "logout_page.php");
    }
}

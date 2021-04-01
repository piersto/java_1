package ru.stqa.pft.mantis.appmanager;

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
}

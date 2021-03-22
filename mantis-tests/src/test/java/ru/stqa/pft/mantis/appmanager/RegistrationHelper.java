package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    }

    public void start(String username, String email) {
    }
}

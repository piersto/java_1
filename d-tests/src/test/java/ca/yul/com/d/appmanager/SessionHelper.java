package ca.yul.com.d.appmanager;

import org.openqa.selenium.WebDriver;

public class SessionHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public SessionHelper(ApplicationManager app) {
        this.app = app;
        wd = app.wd;
    }

    public void openHomePage() {
        wd.get(app.getProperty("web.baseUrl") + "/login.php");
    }
}
























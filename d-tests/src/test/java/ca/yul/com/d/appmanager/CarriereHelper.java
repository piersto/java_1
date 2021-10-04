package ca.yul.com.d.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarriereHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public CarriereHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void openCarrierePage() {
        wd.get("https://www.desjardins.com/fr/carriere.html");
    }

    public int count(String selector) {
        return wd.findElements(By.cssSelector(selector)).size();
    }
}

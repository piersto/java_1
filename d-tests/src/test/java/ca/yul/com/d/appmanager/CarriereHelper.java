package ca.yul.com.d.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarriereHelper extends HelperBase {
    private final ApplicationManager app;
    private WebDriver wd;

    public CarriereHelper(ApplicationManager app) {
        super(app);
        this.app = app;
        wd = app.getDriver();
    }

    public void openCarrierePage() {

        wd.get("https://www.desjardins.com/fr/carriere.html");
        //acceptRegionLanguage();
    }

    public int count(String selector) {

        return wd.findElements(By.cssSelector(selector)).size();
    }

    public String getElementsAttribute(String selector, String attribute) {
        String attributeUnderTest =
                wd.findElement(By.cssSelector(selector)).getAttribute(attribute);
        return attributeUnderTest;
    }
}

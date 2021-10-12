package ca.yul.com.d.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarriereHelper extends HelperBase {
    private final ApplicationManager app;
    private WebDriver wd;
    public String tuileLocator =
            "div[class=\"aem-Grid aem-Grid--xsmall--12 aem-Grid--default--12 \"]>div[class]";

    public CarriereHelper(ApplicationManager app) {
        super(app);
        this.app = app;
        wd = app.getDriver();
    }

    public void openCarrierePage() throws InterruptedException {
        wd.get(app.getProperty("web.baseUrl") + app.getProperty("web.carrierePage"));
        Thread.sleep(50);
        //acceptRegionLanguage();
    }

    public int count(String selector) {

        return wd.findElements(By.cssSelector(selector)).size();
    }

    public int countTuiles() {

        return wd.findElements(By.cssSelector(tuileLocator)).size();
    }

    public String getElementsAttribute(String selector, String attribute) {
        String attributeUnderTest =
                wd.findElement(By.cssSelector(selector)).getAttribute(attribute);
        return attributeUnderTest;
    }
}

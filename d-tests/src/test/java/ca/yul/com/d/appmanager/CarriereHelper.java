package ca.yul.com.d.appmanager;

import com.mysql.cj.util.DnsSrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarriereHelper extends HelperBase {
    private final ApplicationManager app;
    private WebDriver wd;
    public String carrierUrl = "/fr/carriere.html";


    public CarriereHelper(ApplicationManager app) {
        super(app);
        this.app = app;
        wd = app.getDriver();
    }

    public void openCarrierePage() {
        wd.get(app.getProperty("web.baseUrl") + carrierUrl);
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

package ca.yul.com.d.appmanager;

import com.mysql.cj.util.DnsSrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarriereHelper extends HelperBase {
    private final ApplicationManager app;
    private WebDriver wd;
    public String tuile = "div[class=\"aem-Grid aem-Grid--xsmall--12 aem-Grid--default--12 \"]>div[class]";



    public CarriereHelper(ApplicationManager app) {
        super(app);
        this.app = app;
        wd = app.getDriver();
    }

    public void openCarrierePage() {
        wd.get(app.getProperty("web.baseUrl") + app.getProperty("web.carrierePage"));
        //acceptRegionLanguage();
    }

    public int count(String selector) {

        return wd.findElements(By.cssSelector(selector)).size();
    }

    public int countTuiles() {

        return wd.findElements(By.cssSelector(tuile)).size();
    }

    public String getElementsAttribute(String selector, String attribute) {
        String attributeUnderTest =
                wd.findElement(By.cssSelector(selector)).getAttribute(attribute);
        return attributeUnderTest;
    }
}

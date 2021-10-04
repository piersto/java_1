package ca.yul.com.d.tests;

import ca.yul.com.d.appmanager.CarriereHelper;
import org.testng.annotations.Test;

public class QuantityOfBlaBlaBlaTest extends TestBase {

    @Test
    public void testQuantityOFBlaBlaBla() throws InterruptedException {
        app.carriere().openCarrierePage();
        Thread.sleep(50);
        int count = app.carriere()
                .count("div[class=\"aem-Grid aem-Grid--xsmall--12 aem-Grid--default--12 \"]>div[class]");
        Thread.sleep(50);
        assert(count==4);
    }
}

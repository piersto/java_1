package ca.yul.com.d.tests;

import ca.yul.com.d.appmanager.CarriereHelper;
import org.testng.annotations.Test;

public class QuantityOfTuileTest extends TestBase {

    @Test
    public void testQuantityOFTuiles() throws InterruptedException {
        app.carriere().openCarrierePage();
        Thread.sleep(50);
        int count = app.carriere().countTuiles();
        Thread.sleep(50);
        assert(count==4);
    }
}

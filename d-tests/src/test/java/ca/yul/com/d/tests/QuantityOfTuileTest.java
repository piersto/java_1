package ca.yul.com.d.tests;

import ca.yul.com.d.appmanager.CarriereHelper;
import org.testng.annotations.Test;

public class QuantityOfTuileTest extends TestBase {

    @Test
    public void testQuantityOFTuiles() throws InterruptedException {
        app.carriere().openCarrierePage();
        int count = app.carriere().countTuiles();
        assert(count==4);
    }
}

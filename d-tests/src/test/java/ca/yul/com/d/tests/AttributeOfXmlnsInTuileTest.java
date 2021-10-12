package ca.yul.com.d.tests;

import org.testng.annotations.Test;

public class AttributeOfXmlnsInTuileTest extends TestBase {

    @Test
    public void testAttributeOfXmlnsInTuile() throws InterruptedException {
        app.carriere().openCarrierePage();
        String attributeUnderTest =
                app.carriere().getElementsAttribute
                        (" dsd-container > div > div:nth-child(1) > a > svg",
                                "xmlns");
        assert(attributeUnderTest.equals("http://www.w3.org/2000/svg"));
    }
}

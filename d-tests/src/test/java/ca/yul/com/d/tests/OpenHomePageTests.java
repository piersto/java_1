package ca.yul.com.d.tests;

import org.testng.annotations.Test;

public class OpenHomePageTests extends TestBase{

    @Test

    public void testOpenHomePage() {
        app.session().openHomePage();
    }
}

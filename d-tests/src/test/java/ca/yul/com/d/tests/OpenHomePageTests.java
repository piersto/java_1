package ca.yul.com.d.tests;

import org.testng.annotations.Test;

public class OpenHomePageTests extends TestBase{

    @Test

    public void testOpenHomePage() {
        app.session().openHomePage();
        String title = app.session().getTitle();
        assert (title.equals(
                "Services financiers pour particuliers et entreprises | Desjardins"));
    }
}

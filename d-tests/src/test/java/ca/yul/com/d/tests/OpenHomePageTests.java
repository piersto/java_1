package ca.yul.com.d.tests;

import org.testng.annotations.Test;

public class OpenHomePageTests extends TestBase{

    @Test

    public void testOpenHomePage() {
        app.admin().openHomePage();
        String title = app.admin().getTitle();
        assert (title.equals(
                "Services financiers pour particuliers et entreprises | Desjardins"));
    }
}

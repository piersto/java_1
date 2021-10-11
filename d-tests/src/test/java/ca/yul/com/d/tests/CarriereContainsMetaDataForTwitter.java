package ca.yul.com.d.tests;

import ca.yul.com.d.appmanager.HttpSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CarriereContainsMetaDataForTwitter extends TestBase{

    @Test
    public void testContainsMetaDataForTwitterTitle() throws IOException {
        HttpSession session = TestBase.app.newSession();
        Assert.assertTrue(session.
                containsText("<meta property=\"twitter:title\" content=\"Trouver un emploi chez Desjardins\"/>\n"
                        ,"/fr/carriere.html"));
    }

    @Test
    public void testContainsMetaDataForTwitterCard() throws IOException {
        HttpSession session = TestBase.app.newSession();
        Assert.assertTrue(session.
                containsText("<meta property=\"twitter:card\" content=\"summary\"/>\n"
                        ,"/fr/carriere.html"));
    }

    @Test
    public void testContainsMetaDataForTwitterURL() throws IOException {
        HttpSession session = TestBase.app.newSession();
        Assert.assertTrue(session.
                containsText("<meta property=\"twitter:url\" content=\"https://www.desjardins.com/fr/carriere.html\"/>\n"
                        ,"/fr/carriere.html"));
    }

    @Test
    public void testContainsMetaDataForTwitterSite() throws IOException {
        HttpSession session = TestBase.app.newSession();
        Assert.assertTrue(session.
                containsText("<meta property=\"twitter:url\" content=\"https://www.desjardins.com/fr/carriere.html\"/>\n"
                        ,"/fr/carriere.html"));
    }

    @Test
    public void testContainsMetaDataForTwitterImage() throws IOException {
        HttpSession session = TestBase.app.newSession();
        Assert.assertTrue(session.
                containsText("<meta property=\"twitter:image\" content=\"https://www.desjardins.com/fr/carriere.thumb.800.480.png\"/>\n"
                        ,"/fr/carriere.html"));
    }

    @Test
    public void testContainsMetaDataForTwitterDescription() throws IOException {
        HttpSession session = TestBase.app.newSession();
        Assert.assertTrue(session.
                containsText("<meta property=\"twitter:description\" content=\"Faites carrière dans une organisation vraiment humaine où vous développerez vos compétences et ferez une différence dans la communauté. Postulez maintenant!\"/>\n"
                        ,"/fr/carriere.html"));
    }
}

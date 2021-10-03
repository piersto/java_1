package ca.yul.com.d.tests;

import ca.yul.com.d.appmanager.HttpSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MetaContentType extends TestBase{

    @Test
    public void testMetaContentType () throws IOException {
        HttpSession session = app.newSession();
        Assert.assertTrue(session.containsText("<meta http-equiv=\"Content-Type\""));
    }
}

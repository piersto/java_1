package ru.stqa.pft.dcom;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenSalleDeNouvellesPageTests {
    private WebDriver wd;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testOpenSalleDeNouvellesPage() throws Exception {
        wd.get("https://www.desjardins.com/");
        wd.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
        wd.findElement(By.xpath("//li[@id='onglet-apropos']/a/span[2]")).click();
        wd.findElement(By.linkText("Salle de nouvelles")).click();
        wd.findElement(By.id("titrePage")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}

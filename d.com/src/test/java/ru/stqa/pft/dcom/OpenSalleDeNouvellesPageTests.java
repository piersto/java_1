package ru.stqa.pft.dcom;

import org.testng.annotations.*;

import org.openqa.selenium.*;

public class OpenSalleDeNouvellesPageTests {
    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
    }

    @Test
    public void testOpenSalleDeNouvellesPage() throws Exception {
        wd.get("https://www.desjardins.com/");
        wd.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
        wd.findElement(By.xpath("//li[@id='onglet-apropos']/a/span[2]")).click();
        wd.findElement(By.linkText("Salle de nouvelles")).click();
        wd.findElement(By.id("titrePage")).click();
    }

    @AfterMethod(alwaysRun = true)
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

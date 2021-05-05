package ru.stqa.pft.rest.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;
    private String browser;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader
                (new File(String.format("src/test/resources/%s.properties", target))));
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }


    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            }
            wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
            properties.getProperty("web.password");
        }
        return wd;
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }
 }

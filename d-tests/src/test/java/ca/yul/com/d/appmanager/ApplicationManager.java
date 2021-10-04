package ca.yul.com.d.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
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
    private SessionHelper sessionHelper;
    private CarriereHelper carriereHelper;

//    private RegistrationHelper registrationHelper;
//    private FtpHelper ftp;
//    private MailHelper mailHelper;
//    private AdminHelper adminHelper;
//    private SoapHelper soapHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader
                (new File(String.format("src/test/resources/%s.properties", target))));
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public CarriereHelper carriere() {
        if (carriereHelper == null) {
            carriereHelper = new CarriereHelper(this);
        }
        return carriereHelper;
    }
//    public RegistrationHelper registration() {
//        if (registrationHelper == null) {
//            registrationHelper = new RegistrationHelper(this);
//        }
//        return registrationHelper;
//    }
//
//    public AdminHelper admin() {
//        if (adminHelper == null) {
//            adminHelper = new AdminHelper(this);
//        }
//        return adminHelper;
//    }
//
//    public FtpHelper ftp() {
//        if (ftp == null) {
//            ftp = new FtpHelper(this);
//        }
//        return ftp;
//    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            } else if (browser.equals(BrowserType.EDGE)) {
                wd = new EdgeDriver();
            }
            wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
            properties.getProperty("web.adminPassword");
        }
        return wd;
    }

//    public MailHelper mail() {
//        if (mailHelper == null) {
//            mailHelper = new MailHelper(this);
//        }
//        return mailHelper;
//    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

//    public SoapHelper soap () {
//        if (soapHelper == null) {
//            soapHelper = new SoapHelper(this);
//        }
//        return soapHelper;
//    }

}

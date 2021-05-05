package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    private Executor getExecutor() {
        return  Executor.newInstance()
                .auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    private boolean  isIssueOpen(int issueId) throws IOException {
        String issueJson = getExecutor().execute(Request
                .Get(app.getProperty("web.baseUrl") + "api/issues/" + issueId + ".json")).returnContent().asString();
        System.out.println("This is my json: " + issueJson);
        Gson gson = new Gson();
        Issue issue = gson.fromJson(issueJson, Issue.class);
        String state = issue.getState_name();
        System.out.println("This is my state: " + state);
        return !state.equals("Closed");
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.stop();
    }
}

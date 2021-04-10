package ru.stqa.pft.rest;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestTests {

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue();
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        Assert.assertEquals(oldIssues, newIssues);

    }

    private Set<Issue> getIssues() throws IOException {
        String jason = getExecutor().execute(Request
                .Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
        return null;
    }

    private Executor getExecutor() {
        return  Executor.newInstance().auth("ad348266150ab4beafbfa5e67ec06368", "");
    }

    private int createIssue(Issue newIssue) {
        return 0;
    }
}

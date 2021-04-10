package ru.stqa.pft.rest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class RestTests {

    @Test
    public void testCreateIssue() {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue();
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        Assert.assertEquals(oldIssues, newIssues);

    }

    private Set<Issue> getIssues() {
        return null;
    }

    private int createIssue(Issue newIssue) {
        return 0;
    }
}

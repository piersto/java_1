package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
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
        String json = getExecutor().execute(Request
                .Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private Executor getExecutor() {
        return  Executor.newInstance()
                .auth("ad348266150ab4beafbfa5e67ec06368", "");
    }

    private int createIssue(Issue newIssue) {
        return 0;
    }
}

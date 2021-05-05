package ru.stqa.pft.rest.tests;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.annotations.Test;

import java.io.IOException;

@Test

public class GetIssueStateTest extends TestBase {

    public void testGetIssueState() throws IOException {
        String issueState = getExecutor().execute(Request
                .Get("https://bugify.stqa.ru/api/issues/1048.json")).returnContent().asString();
        System.out.println(issueState);

    }

    private Executor getExecutor() {
        return  Executor.newInstance()
                .auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}

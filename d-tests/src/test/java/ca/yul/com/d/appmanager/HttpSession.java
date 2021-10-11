package ca.yul.com.d.appmanager;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpSession {
    private CloseableHttpClient httpclient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    private String geTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public boolean containsText(String text, String pageURL) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + pageURL);
        CloseableHttpResponse response = httpclient.execute(get);
        String body = geTextFrom(response);
        return body.contains(String.format(text));
    }

    public boolean containsText(String text) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl"));
        CloseableHttpResponse response = httpclient.execute(get);
        String body = geTextFrom(response);
        return body.contains(String.format(text));
    }
}

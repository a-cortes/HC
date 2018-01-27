package com.realestate.hcrawler.webcontext;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;

import java.io.IOException;

public class HtmlUnitRequester implements Requester {
    WebClient client;

    public HtmlUnitRequester() {
        //TODO: Make browser version parametrized
        client = new WebClient(BrowserVersion.CHROME);

        WebClientOptions options = client.getOptions();

        options.setJavaScriptEnabled(true);
        options.setThrowExceptionOnScriptError(false);
        options.setThrowExceptionOnFailingStatusCode(false);
    }

    @Override
    public WebContext get(String url) throws IOException {
        return new HtmlUnitWebContext(client.getPage(url));
    }

    @Override
    public void close() {
        client.close();
    }
}

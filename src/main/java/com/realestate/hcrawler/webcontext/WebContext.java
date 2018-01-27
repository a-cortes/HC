package com.realestate.hcrawler.webcontext;

import java.util.List;

public interface WebContext {
    WebContext find(String cssSelectorOrXpath);

    List<WebContext> findAll(String cssSelectorOrXpath);

    String getAttribute(String name);

    String getText();
}

package com.realestate.hcrawler.webcontext;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;

import java.util.List;
import java.util.stream.Collectors;

import static com.realestate.hcrawler.util.UtilMethods.isXpath;

public class HtmlUnitWebContext implements WebContext {
    DomNode node;

    public HtmlUnitWebContext(DomNode node) {
        this.node = node;
    }

    @Override
    public WebContext find(String cssSelectorOrXpath) {
        DomNode result;

        if (isXpath(cssSelectorOrXpath)) {
            result = (DomNode) node
                    .getByXPath(cssSelectorOrXpath)
                    .get(0);
        } else {
            result = node.querySelector(cssSelectorOrXpath);
        }

        return new HtmlUnitWebContext(result);
    }

    @Override
    public List<WebContext> findAll(String cssSelectorOrXpath) {
        List<WebContext> results;

        if (isXpath(cssSelectorOrXpath)) {
            results = node.getByXPath(cssSelectorOrXpath)
                    .stream()
                    .map(n -> new HtmlUnitWebContext( (DomNode)n ))
                    .collect(Collectors.toList());
        } else {
            results = node.querySelectorAll(cssSelectorOrXpath)
                    .stream()
                    .map(HtmlUnitWebContext::new)
                    .collect(Collectors.toList());
        }

        return results;
    }

    @Override
    public String getAttribute(String name) {
        return ((DomElement)node).getAttribute(name);
    }

    @Override
    public String getText() {
        return node.getTextContent();
    }
}

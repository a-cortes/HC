package com.realestate.hcrawler.webcontext;


public interface Requester {

    WebContext get(String url) throws Exception;

    void close();
}

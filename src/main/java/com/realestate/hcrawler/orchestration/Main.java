package com.realestate.hcrawler.orchestration;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");

        //TODO: Max instances from config file
        Orchestrator.run(2);
    }
}

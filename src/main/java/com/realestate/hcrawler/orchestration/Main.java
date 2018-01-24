package com.realestate.hcrawler.orchestration;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\acortes\\\\Desktop\\\\code\\\\Tools\\\\drivers\\\\chromedriver.exe");

        //TODO: Max instances from config file
        Orchestrator.run(2);
    }
}

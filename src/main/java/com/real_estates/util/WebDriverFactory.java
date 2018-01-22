package com.real_estates.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    private static List<WebDriverThread> webDriverThreadPool = Collections.synchronizedList(new ArrayList<WebDriverThread>());
    private static ThreadLocal<WebDriverThread> driverThread;
    
    public static void instantiateDriverObject() {
        driverThread = new ThreadLocal<WebDriverThread>() {
            @Override
            protected WebDriverThread initialValue() {
                WebDriverThread webDriverThread = new WebDriverThread();
                webDriverThreadPool.add(webDriverThread);
                return webDriverThread;
            }
        };
    }

    public static WebDriver getDriver() throws Exception {
        return driverThread.get().getDriver();
    }

    
    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }

    
    public static void closeDriverObjects() {
    	System.out.println("Tests finished");
        for (WebDriverThread webDriverThread : webDriverThreadPool) {
           webDriverThread.quitDriver();
        }
    }
}
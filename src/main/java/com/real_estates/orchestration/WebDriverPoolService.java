package com.real_estates.orchestration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverPoolService {

    public static WebDriver get() {
        return new ChromeDriver();
    }

    public static void release(WebDriver driver) {
        driver.quit();
    }
}

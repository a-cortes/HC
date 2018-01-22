package com.real_estates.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverThread {

	private WebDriver webdriver;

	private final String operatingSystem = System.getProperty("os.name").toUpperCase();
	private final String systemArchitecture = System.getProperty("os.arch");

	public WebDriver getDriver() throws Exception {

		if (null == webdriver) {
			System.out.println(" ");
			System.out.println("Current Operating System: " + operatingSystem);
			System.out.println("Current Architecture: " + systemArchitecture);
			System.out.println("Current Browser Selection: Chrome");
			System.out.println(" ");
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\acortes\\Desktop\\code\\Tools\\drivers\\chromedriver.exe");

			webdriver = new ChromeDriver();
		}

		return webdriver;
	}

	public void quitDriver() {
		if (null != webdriver) {
			webdriver.quit();
			webdriver = null;
		}
	}
}
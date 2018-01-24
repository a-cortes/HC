package com.realestate.hcrawler.scrapers;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class MercadoLibreSteps {

	WebDriver driver;
	WebDriverWait wait;
	WebElement element;
	String cityUrl;
	LinkedList<String> hoodUrls;
	Thread hoodThread;

	@Before
	public void setUp() {
		System.out.println("Start");
		System.setProperty("webdriver.chrome.driver",
				"C:\\\\Users\\\\acortes\\\\Desktop\\\\code\\\\Tools\\\\drivers\\\\chromedriver.exe");// Create a new
																										// instance of
		// the Firefox driver
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 60);
		cityUrl = "http://inmuebles.mercadolibre.com.mx/venta/queretaro/queretaro/";
		hoodUrls = new LinkedList();
		hoodThread = new Thread(new HoodThread());
		hoodThread.start();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Given("^Entramos a pagina principal$")
	public void entramos_a_pagina_principal() throws Throwable {
		driver.get(cityUrl);
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"id_neighborhood\"]/label")));
		element.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"input-neighborhood-filter\"]")));
	}

	@Given("^usuario selecciona una a una las paginas$")
	public void usuario_selecciona_una_a_una_las_paginas() throws Throwable {

		List<WebElement> hoods = driver.findElements(By.className("neighborhood_filter_checkbox"));
		Actions actions = new Actions(driver);
		int size = hoods.size();
		for (int i = 0; i < size; i++) {
			driver.get(cityUrl);
			element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"id_neighborhood\"]/label")));
			element.click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("input-neighborhood-filter")));
			hoods = driver.findElements(By.className("neighborhood_filter_checkbox"));
			WebElement hoodSelect = hoods.get(i);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			while (!driver.findElement(By.id("submit-neighborhood-filter")).isDisplayed() || !hoodSelect.isSelected()) {

				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',"
						+ "true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject)"
						+ "{ arguments[0].fireEvent('onmouseover');}";

				String onClickScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('click',"
						+ "true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('onclick');}";

				js.executeScript(mouseOverScript, hoodSelect);

				js.executeScript(onClickScript, hoodSelect);
				Thread.sleep(500);
			}

			wait.until(ExpectedConditions.elementToBeClickable(By.id("submit-neighborhood-filter")));
			driver.findElement(By.id("submit-neighborhood-filter")).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(By.className("nav-search-input")));
			String currentUrl = driver.getCurrentUrl();
			//System.out.println(currentUrl);
			hoodUrls.add(currentUrl);

		}
		Thread.sleep(10000);

	}

	@Then("^Regresa lista de Urls$")
	public void regresa_lista_de_Urls() throws Throwable {

	}

	class HoodThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				String hoodUrl = hoodUrls.poll();

				if (hoodUrl != null) {
					System.out.println("Searching houses in: "+hoodUrl);
				} 
				else {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

	}

}

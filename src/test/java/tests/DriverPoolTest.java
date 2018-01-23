package tests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.real_estates.util.WebDriverFactory;

public class DriverPoolTest {

	@Test
	public void test() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");

		int limit = 2;
		
		ExecutorService executor = Executors.newFixedThreadPool(limit);
		
		WebDriverFactory.instantiateDriverObject();
		Runnable thread = new WorkerThread("https://inmuebles.mercadolibre.com.mx/casas/venta/baja-california/tijuana/");
		executor.execute(thread);
		
		WebDriverFactory.instantiateDriverObject();
		Runnable thread2 = new WorkerThread("https://inmuebles.mercadolibre.com.mx/casas/venta/queretaro/queretaro/");
		executor.execute(thread2);
		
		Runnable thread3 = new WorkerThread("https://inmuebles.mercadolibre.com.mx/casas/venta/michoacan/morelia/");
		executor.execute(thread3);
		
		Runnable thread4 = new WorkerThread("https://inmuebles.mercadolibre.com.mx/venta/queretaro/san-juan-del-rio/");
		executor.execute(thread4);

		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}

	public class WorkerThread implements Runnable {

		private String url;

		public WorkerThread(String s) {
			this.url = s;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " Start. Command = " + url);
			processCommand();
			
			WebDriver driver;
			try {
				driver = WebDriverFactory.getDriver();
				driver.get(url);
				WebDriverFactory.clearCookies();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + " End.");
		}

		private void processCommand() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return this.url;
		}
	}
}

package com.hcrawler.scrapers;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class MercadoLibreScrapperMethods {
	public static List<String> getHousesLinksFromUrl(String hoodUrl) {
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);

		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

		ArrayList<String> housesLinks = new ArrayList<String>();

		// Get page to scrap
		HtmlPage hoodPage = null;
		try {
			hoodPage = webClient.getPage(hoodUrl);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (hoodPage != null) {
			HtmlElement linkNext = null;
			// Go through all pages
			do {
				linkNext = null;
				try {
					linkNext = (HtmlElement) hoodPage
							.getFirstByXPath("//*[@id=\"results-section\"]/div[2]/ul/li[last()]/a");

					if (!linkNext.getAttribute("href").contains("#")) {
						hoodPage = linkNext.click();
						System.out.println(hoodPage.getUrl());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			} while (linkNext != null && !linkNext.getAttribute("href").contains("#"));
			System.out.println("End of pages of" + hoodUrl);
		}
		webClient.close();
		return housesLinks;
	}

}

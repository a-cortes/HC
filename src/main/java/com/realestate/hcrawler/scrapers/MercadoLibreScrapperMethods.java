package com.realestate.hcrawler.scrapers;

import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.realestate.housedata.model.House;

public class MercadoLibreScrapperMethods {
	private static WebClient getWebClient() {
		
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);

		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		return webClient;
		
	}
	
	
	public static void scrapHousesFromUrl(String hoodUrl) {
		WebClient webClient = getWebClient();

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
		
	}
	
	/*
	 * 
	 * Completes info for the house gotten in index
	 * 
	 * 
	 */
	public static House completeHouseInfo(House house) {
		WebClient webClient = getWebClient();
		
		int constructionMts = 0;
		int fieldMts = 0;
		String type = null;
		String houseUrl = house.getUrl();

		// Get page to scrap
		HtmlPage housePage = null;
		try {
			housePage = webClient.getPage(houseUrl);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if(housePage != null) {
			String houseXml = housePage.asXml();
			String mtsAux = null;

			// Metros Construccion
			if (houseXml.contains("Superficie de construcción:")) {
				houseXml = houseXml.substring(houseXml.indexOf("Superficie de construcción:"),
						houseXml.length());
				mtsAux = houseXml.substring(houseXml.indexOf("<span class=\"attribute-value\">"),
						houseXml.indexOf("m²"));
				mtsAux = mtsAux.replaceAll("\\D+", "");
				constructionMts = Integer.valueOf(mtsAux);
			}

			// Metros Terreno
			if (houseXml.contains("Superficie de terreno:")) {
				houseXml = houseXml.substring(houseXml.indexOf("Superficie de terreno:"),
						houseXml.length());
				mtsAux = houseXml.substring(houseXml.indexOf("<span class=\"attribute-value\">"),
						houseXml.indexOf("m²"));
				mtsAux = mtsAux.replaceAll("\\D+", "");
				fieldMts = Integer.valueOf(mtsAux);
			}
			// Tipo de inmueble
			
			if (houseUrl.contains("casa.mercadolibre"))
				type = "Casa";
			if (houseUrl.contains("departamento.mercadolibre"))
				type = "Departamento";
			if (houseUrl.contains("terreno.mercadolibre"))
				type = "Terreno";
			
			System.out.println(type + " "+constructionMts+" "+fieldMts);
			
			house.setConstructionMts(constructionMts);
			house.setFieldMts(fieldMts);
			house.setType(type);
		}
		
		return house;
		
	}

}

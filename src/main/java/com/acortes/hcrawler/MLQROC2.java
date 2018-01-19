/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acortes.hcrawler;

import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.acortes.hcrawler.configuration.AppConfig;
import com.acortes.hcrawler.model.House;
import com.acortes.hcrawler.service.HouseService;
import com.acortes.hcrawler.util.HouseFactory;
import com.acortes.hcrawler.util.UtilMethods;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class MLQROC2 {

	public static void main(String[] args) throws IOException, ParseException {
		// Hibernate Objects
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		HouseService hibService = (HouseService) context.getBean("houseService");

		// Start crawling
		System.out.println("Starting");

		// Client
		// WebClient webClient = new
		// WebClient(BrowserVersion.CHROME,"172.28.97.24", 8080);
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
		
		webClient.getOptions().setJavaScriptEnabled(false);
	 	webClient.getOptions().setThrowExceptionOnScriptError(false);
	 	webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

		
			HtmlPage pageCity = webClient.getPage("http://inmuebles.mercadolibre.com.mx/venta/queretaro/queretaro/");
			//System.out.println(pageColonia.asText());
			List coloniasPrueba = (List) pageCity.getByXPath("//*[@id=\"neighborhood-filter-form\"]/div/div[3]/div/div/div[2]/div/div/label");
			System.out.println(coloniasPrueba.size());
			for (Object coloniaHtmlO : coloniasPrueba) {
				HtmlElement coloniaHtml =  (HtmlElement) coloniaHtmlO;
				String url = "https://inmuebles.mercadolibre.com.mx/venta/queretaro/queretaro/" + 
						coloniaHtml.getTextContent().replaceAll(" ", "-").replace("", "")+"/";
				url = Normalizer.normalize(url, Normalizer.Form.NFD);
				url = url.replaceAll("[^\\p{ASCII}]", "");
				System.out.println(url);
//				HtmlPage hoodPage = HoodController.hoodCrawler(url);
//				if(hoodPage == null) {
//					System.out.println("Error " + url);
//				
//				}
				
				
				
			}
			if (true) return;
			
			/// GET all pages
			HtmlPage pageColonia = null;
			HtmlAnchor linkSiguiente = null;
			do {
				linkSiguiente = null;
				try {
					linkSiguiente = pageColonia.getFirstByXPath("//*[@id=\"results-section\"]/div[2]/ul/li[12]/a");
					pageColonia = linkSiguiente.click();
				} catch (Exception ex) {
					System.out.println("End of pages of");
				}

				// the previwe of houses comes li elements there is one big and
				// one small we take the bigger cuz it has the price
				DomNodeList<DomElement> casas = pageColonia.getElementsByTagName("li");
				for (DomElement casa : casas) {
					String casaCode = casa.asXml();

					/*
					 * getting the price from the house page doesnt work so we
					 * are getting it from the preview casaCode
					 */
					if (casaCode.contains("class=\"item-link item__js-link\"") && casaCode.contains("<div")) {

						String linkCasa = null;
						long precio = 0;
						int mtsConstruccion = 0;
						int mtsTerreno = 0;

						try {
							// remove useless first part
							casaCode = casaCode.substring(casaCode.indexOf("<span class=\"ch-price\">"),
									casaCode.length());
							// get link for the house
							linkCasa = casaCode.substring(casaCode.indexOf("<a href=\"") + 9,
									casaCode.indexOf("\" class=\"\">"));
							// get price of the house
							String precioS = casaCode.substring(casaCode.indexOf("<span class=\"ch-price\">") + 23,
									casaCode.indexOf("</span>"));
							precioS = precioS.replaceAll("\\D+", "");
							precio = Integer.valueOf(precioS);

							// System.out.println("-------"+linkCasa + "-------"
							// + precio+"------");

						} catch (Exception ex) {
							System.out.println("Error at getting house information:" + casaCode);
						}
						/* if we have a valid link for a house */
						if (linkCasa != null) {
							/*
							 * <span class="attribute-key"> Superficie de
							 * construcción: </span> <span
							 * class="attribute-value"> 183 m² </span> </li>
							 * <li> <span class="attribute-key"> Superficie de
							 * terreno: </span> <span class="attribute-value">
							 * 192 m² </span>
							 * 
							 */
							//// Check if house is already in DB
							House houseDB = hibService.findByUrl(linkCasa);
							if (houseDB != null) {
								System.out.println("Casa en DB");
								Date date = UtilMethods.formatDate(new Date());
								House h = HouseFactory.createHouse(date, houseDB.getCiudad(), houseDB.getColonia(),
										houseDB.getUrl(), houseDB.getMtsTerreno(), houseDB.getMtsConstruccion(),
										houseDB.getTipo(), houseDB.getPrice(), houseDB.getSource());
								hibService.saveHouse(h);
							} else {
								////
								try {

									HtmlPage pageCasa = webClient.getPage(linkCasa);
									casaCode = pageCasa.asXml();
									String mtsS = null;

									// Metros Construccion
									if (casaCode.contains("Superficie de construcción:")) {
										casaCode = casaCode.substring(casaCode.indexOf("Superficie de construcción:"),
												casaCode.length());
										mtsS = casaCode.substring(casaCode.indexOf("<span class=\"attribute-value\">"),
												casaCode.indexOf("m²"));
										mtsS = mtsS.replaceAll("\\D+", "");
										mtsConstruccion = Integer.valueOf(mtsS);
									}

									// Metros Terreno
									if (casaCode.contains("Superficie de terreno:")) {
										casaCode = casaCode.substring(casaCode.indexOf("Superficie de terreno:"),
												casaCode.length());
										mtsS = casaCode.substring(casaCode.indexOf("<span class=\"attribute-value\">"),
												casaCode.indexOf("m²"));
										mtsS = mtsS.replaceAll("\\D+", "");
										mtsTerreno = Integer.valueOf(mtsS);
									}
									// Tipo de inmueble
									String tipo = null;
									if (linkCasa.contains("casa.mercadolibre"))
										tipo = "Casa";
									if (linkCasa.contains("departamento.mercadolibre"))
										tipo = "Departamento";
									if (linkCasa.contains("terreno.mercadolibre"))
										tipo = "Terreno";

									// Create house Bean
									System.out.println("Cons ++++" + mtsConstruccion + " TEr +++" + mtsTerreno + " Link"
											+ linkCasa + "Tipo " + tipo);

									Date date = UtilMethods.formatDate(new Date());

									House house = HouseFactory.createHouse(date, "Queretaro", "", linkCasa,
											mtsTerreno, mtsConstruccion, tipo, precio, "ML");

									hibService.saveHouse(house);
									System.out.println(hibService.findById(house.getUrlDate()).getPrice());
									;

								} catch (Exception ex) {
									ex.printStackTrace();
									// System.out.println("Error en datos de
									// casa: "+ linkCasa +" ------>"+ casaCode);

								}

							}
						}
					}

				}
				// linkSiguiente = null; //Only first page

			} while (linkSiguiente != null && false);
			/// end of pages
	}

}

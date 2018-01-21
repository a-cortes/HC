/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hcrawler;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hcrawler.config.AppConfig;
import com.hcrawler.util.HouseFactory;
import com.hcrawler.util.UtilMethods;
import com.housedata.model.House;
import com.housedata.service.HouseService;

public class MLQROCrawler {

	public static void main(String[] args) throws IOException, ParseException {
		// Hibernate Objects
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		HouseService hibService = (HouseService) context.getBean("houseService");
		// {
		// ///Hood situation calc
		// System.out.println("HOOD:");
		// long priceSum=0,mtsTerSum=0,mtsConsSum=0;
		// int contCasas=0;
		// Date date = UtilMethods.formatDate(new Date());
		// List<House> casasColonia =
		// hibService.findAllHousesByCityHood("Queretaro", "Juriquilla",date);
		//
		// for (House house : casasColonia){
		// priceSum+=house.getPrice();
		// mtsTerSum+=house.getMtsTerreno();
		// mtsConsSum+=house.getMtsConstruccion();
		// contCasas++;
		//
		// }
		// System.out.println("precio avg: "+(priceSum/contCasas)+
		// " Precio cons avg: "+ (mtsConsSum/contCasas) + " Precio ter avg:
		// "+(mtsTerSum/contCasas));
		// if(true)return;
		// }

		// Start crawling
		System.out.println("Starting");

		// Client
		// WebClient webClient = new
		// WebClient(BrowserVersion.CHROME,"172.28.97.24", 8080);
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
		
		webClient.getOptions().setJavaScriptEnabled(true);
	 	webClient.getOptions().setThrowExceptionOnScriptError(false);
	 	webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

		// Get first page of the city
		HtmlPage pageCiudad = null;

		try {
			pageCiudad = webClient.getPage("http://inmuebles.mercadolibre.com.mx/venta/queretaro/queretaro/");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage().substring(0, 2000));
		}
		//System.out.println(pageCiudad.asText());
		
		
		HtmlElement mas = pageCiudad.getFirstByXPath("//*[@id=\"id_neighborhood\"]/label");

		// open more... menu
		HtmlPage pageColonian = mas.click();
		System.out.println(pageColonian.asText());
		
		if(true)return;
		// get all the hoods
		List colonias = (List) pageCiudad.getByXPath("//*[@id=\"id_neighborhood\"]/dd");

		colonias.remove(colonias.size() - 1);
		colonias.addAll((List) pageCiudad.getByXPath("//*[@id=\"id_neighborhood\"]/dd[10]/dl/dd"));

		// Lood on the hoods
		// to save all the house of current hood
		colonias: for (Object coloniaHtmlO : colonias) {
			HtmlElement coloniaHtml =  (HtmlElement) coloniaHtmlO;
			DomNodeList<HtmlElement> elementsByTagName = coloniaHtml.getElementsByTagName("a");
			String colonia = null;
			for (HtmlElement a : elementsByTagName) {
				colonia = a.asText();
			}
			System.out.println(colonia + "----------------------");
			/// Get first page for the colonia

			HtmlAnchor link = pageCiudad.getAnchorByText(colonia);
			HtmlPage pageColonia = link.click();

			/// GET all pages
			HtmlAnchor linkSiguiente = null;
			do {
				linkSiguiente = null;
				try {
					linkSiguiente = pageColonia.getAnchorByText("Siguiente >");
					pageColonia = linkSiguiente.click();
				} catch (Exception ex) {
					System.out.println("End of pages of" + colonia);
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
//								House h = HouseFactory.createHouse(date, houseDB.getCiudad(), houseDB.getColonia(),
//										houseDB.getUrl(), houseDB.getMtsTerreno(), houseDB.getMtsConstruccion(),
//										houseDB.getTipo(), houseDB.getPrice(), houseDB.getSource());
//								hibService.saveHouse(h);
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

									House house = HouseFactory.createHouse(date, "Queretaro", colonia, linkCasa,
											mtsTerreno, mtsConstruccion, tipo, precio, "ML");

									hibService.saveHouse(house);
									//System.out.println(hibService.findById(house.getUrlDate()).getPrice());
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

			break colonias;// Only one hood

		}
	}

}

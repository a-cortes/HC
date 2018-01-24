/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realestate.hcrawler.util;

import java.util.Date;

import com.realestate.housedata.model.PropertySale;

public class HouseFactory {
	public static PropertySale createHouse() {

		return new PropertySale();
	}

	public static PropertySale createHouse(Date date,String country, String state, String ciudad, String colonia, String linkCasa, int mtsTerreno,
			int mtsConstruccion, String tipo, long price, String source) {

		//String dateS = UtilMethods.formatDateString(date, "yyyy-MM-dd");
		PropertySale house = new PropertySale();
		house.setState(state);
		house.setCountry(country);
		house.setCity(ciudad);
		house.setHood(colonia);
		house.setUrl(linkCasa);
		house.setBuildingMts(mtsConstruccion);
		house.setLotMts(mtsTerreno);
		house.setType(tipo);
		house.setPrice(price);
		house.setRegisterDate(date);
		house.setSource(source);
		
		
		try {
			if (mtsConstruccion != 0)
				house.setBuildingMtPrice(price / mtsConstruccion);
			if (mtsTerreno != 0)
				house.setLotMtPrice(price / mtsTerreno);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return house;

	}
	public static PropertySale createStubHouse() {
		String dateS = UtilMethods.formatDateString(new Date(), "yyyy-MM-dd");
		PropertySale house = new PropertySale();
		house.setCity("Morelia");
		house.setHood("Reforma");
		house.setUrl("TEST");
		house.setBuildingMtPrice(123);
		house.setLotMts(123);
		house.setType("TEST");
		house.setPrice(123);
		house.setRegisterDate(new Date());
		house.setSource("TEST");
		house.setCountry("Mexico");
		house.setState("Michoacan");
		
		return house;
		
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hcrawler.util;

import java.util.Date;

import com.housedata.model.House;

public class HouseFactory {
	public static House createHouse() {

		return new House();
	}

	public static House createHouse(Date date, String ciudad, String colonia, String linkCasa, int mtsTerreno,
			int mtsConstruccion, String tipo, long price, String source) {

		String dateS = UtilMethods.formatDateString(date, "yyyy-MM-dd");
		House house = new House();
		house.setCity(ciudad);
		house.setHood(colonia);
		house.setUrl(linkCasa);
		house.setConstructionMts(mtsConstruccion);
		house.setFieldMts(mtsTerreno);
		house.setType(tipo);
		house.setPrice(price);
		house.setRegisterDate(date);
		house.setSource(source);
		try {
			if (mtsConstruccion != 0)
				house.setConstructionMtPrice(price / mtsConstruccion);
			if (mtsTerreno != 0)
				house.setFieldMtPrice(price / mtsTerreno);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return house;

	}
	public static House createStubHouse() {
		String dateS = UtilMethods.formatDateString(new Date(), "yyyy-MM-dd");
		House house = new House();
		house.setCity("Morelia");
		house.setHood("Reforma");
		house.setUrl("TEST");
		house.setConstructionMts(123);
		house.setFieldMts(123);
		house.setType("TEST");
		house.setPrice(123);
		house.setRegisterDate(new Date());
		house.setSource("TEST");
		
		return house;
		
	}

}

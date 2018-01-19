/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acortes.hcrawler.util;

import java.util.Date;

import com.acortes.hcrawler.model.House;

public class HouseFactory {
	public static House createHouse() {

		return new House();
	}

	public static House createHouse(Date date, String ciudad, String colonia, String linkCasa, int mtsTerreno,
			int mtsConstruccion, String tipo, long price, String source) {

		String dateS = UtilMethods.formatDateString(date, "yyyy-MM-dd");
		House house = new House();
		house.setCiudad(ciudad);
		house.setColonia(colonia);
		house.setUrl(linkCasa);
		house.setMtsConstruccion(mtsConstruccion);
		house.setMtsTerreno(mtsTerreno);
		house.setTipo(tipo);
		house.setPrice(price);
		house.setUrlDate(linkCasa + " DATE:" + dateS);
		house.setFechaRegistro(date);
		house.setSource(source);
		try {
			if (mtsConstruccion != 0)
				house.setPrecioMtCons(price / mtsConstruccion);
			if (mtsTerreno != 0)
				house.setPrecioMtTerreno(price / mtsTerreno);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return house;

	}
	public static House createStubHouse() {
		String dateS = UtilMethods.formatDateString(new Date(), "yyyy-MM-dd");
		House house = new House();
		house.setCiudad("Morelia");
		house.setColonia("Reforma");
		house.setUrl("NULL");
		house.setMtsConstruccion(123);
		house.setMtsTerreno(123);
		house.setTipo("NULL");
		house.setPrice(123);
		house.setUrlDate(Math.random() + " DATE:" + dateS);
		house.setFechaRegistro(new Date());
		house.setSource("TEST");
		
		return house;
		
	}

}

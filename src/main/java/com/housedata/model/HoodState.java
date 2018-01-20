package com.housedata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Hood")
public class HoodState {

	@Id
	@Column(name = "UrlDate", unique = true, nullable = false)
	private String CityHoodDate;
	private String dateRegistered;
	private String name;
	private String city;
	private int numberHouses;
	private double priceAvg;
	private double priceConsAvg;
	private double priceTerAvg;

	public String getCityHoodDate() {
		return CityHoodDate;
	}

	public void setCityHoodDate(String cityHoodDate) {
		CityHoodDate = cityHoodDate;
	}

	public String getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(String dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getNumberHouses() {
		return numberHouses;
	}

	public void setNumberHouses(int numberHouses) {
		this.numberHouses = numberHouses;
	}

	public double getPriceAvg() {
		return priceAvg;
	}

	public void setPriceAvg(double priceAvg) {
		this.priceAvg = priceAvg;
	}

	public double getPriceConsAvg() {
		return priceConsAvg;
	}

	public void setPriceConsAvg(double priceConsAvg) {
		this.priceConsAvg = priceConsAvg;
	}

	public double getPriceTerAvg() {
		return priceTerAvg;
	}

	public void setPriceTerAvg(double priceTerAvg) {
		this.priceTerAvg = priceTerAvg;
	}

}

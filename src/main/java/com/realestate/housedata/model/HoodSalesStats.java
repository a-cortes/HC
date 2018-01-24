package com.realestate.housedata.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Hood")
public class HoodSalesStats {

	@Id
	int id;
	
	private String neighborhood;
	private String city;
	private String state;
	private String country;
	
	private String dateRegistered;

	private int numberHouses;
	private double priceAvg;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDateRegistered() {
		return dateRegistered;
	}
	public void setDateRegistered(String dateRegistered) {
		this.dateRegistered = dateRegistered;
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

	
	

}

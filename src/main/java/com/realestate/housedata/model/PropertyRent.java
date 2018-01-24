/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realestate.housedata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cadrian
 */
@Entity
@Table(name = "propertysale")
public class PropertyRent {

	@Id
	int id;

	@Column(name = "Price", nullable = true)
	private long price;

	@Column(name = "Url", nullable = true)
	private String url;

	private int lotMts;
	private int buildingMts;
	private double lotMtPrice;
	private double buildingMtPrice;
	private String type;
	private Date registerDate;
	private String source;
	// Address variables
	@Column(name = "Neighborhood", nullable = false)
	private String neighborhood;
	private String city;
	private String state;
	private String country;

	public int getBuildingMts() {
		return buildingMts;
	}

	public void setBuildingMts(int buildingMts) {
		this.buildingMts = buildingMts;
	}

	public double getBuildingMtPrice() {
		return buildingMtPrice;
	}

	public void setBuildingMtPrice(double buildingMtPrice) {
		this.buildingMtPrice = buildingMtPrice;
	}

	public int getLotMts() {
		return lotMts;
	}

	public void setLotMts(int lotMts) {
		this.lotMts = lotMts;
	}

	public double getLotMtPrice() {
		return lotMtPrice;
	}

	public void setLotMtPrice(double lotMtPrice) {
		this.lotMtPrice = lotMtPrice;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHood() {
		return neighborhood;
	}

	public void setHood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}

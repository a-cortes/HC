/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.housedata.model;

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
@Table(name = "House")
public class House {

	@Id
	int id;

	@Column(name = "Price", nullable = true)
	private long price;

	@Column(name = "Url", nullable = true)
	private String url;

	@Column(name = "Hood", nullable = false)
	private String hood;

	private int fieldMts;
	private int constructionMts;
	private String city;
	private double fieldMtPrice;
	private double constructionMtPrice;
	private String type;
	private Date registerDate;
	private String source;
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
		return hood;
	}
	public void setHood(String hood) {
		this.hood = hood;
	}
	public int getFieldMts() {
		return fieldMts;
	}
	public void setFieldMts(int fieldMts) {
		this.fieldMts = fieldMts;
	}
	public int getConstructionMts() {
		return constructionMts;
	}
	public void setConstructionMts(int constructionMts) {
		this.constructionMts = constructionMts;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getFieldMtPrice() {
		return fieldMtPrice;
	}
	public void setFieldMtPrice(double fieldMtPrice) {
		this.fieldMtPrice = fieldMtPrice;
	}
	public double getConstructionMtPrice() {
		return constructionMtPrice;
	}
	public void setConstructionMtPrice(double constructionMtPrice) {
		this.constructionMtPrice = constructionMtPrice;
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

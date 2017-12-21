/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acortes.hcrawler.model;

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
	@Column(name = "UrlDate", unique = true, nullable = false)
	String urlDate;

	@Column(name = "Precio", nullable = true)
	private long price;

	@Column(name = "Url", nullable = true)
	private String url;

	@Column(name = "Colonia", nullable = false)
	private String colonia;

	private int mtsTerreno;
	private int mtsConstruccion;
	private String ciudad;
	private double precioMtTerreno;
	private double precioMtCons;
	private String tipo;
	private Date fechaRegistro;
	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getUrlDate() {
		return urlDate;
	}

	public void setUrlDate(String urlDate) {
		this.urlDate = urlDate;
	}

	public int getMtsTerreno() {
		return mtsTerreno;
	}

	public void setMtsTerreno(int mtsTerreno) {
		this.mtsTerreno = mtsTerreno;
	}

	public int getMtsConstruccion() {
		return mtsConstruccion;
	}

	public void setMtsConstruccion(int mtsConstruccion) {
		this.mtsConstruccion = mtsConstruccion;
	}

	public double getPrecioMtTerreno() {
		return precioMtTerreno;
	}

	public void setPrecioMtTerreno(double precioMtTerreno) {
		this.precioMtTerreno = precioMtTerreno;
	}

	public double getPrecioMtCons() {
		return precioMtCons;
	}

	public void setPrecioMtCons(double precioMtCons) {
		this.precioMtCons = precioMtCons;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

}

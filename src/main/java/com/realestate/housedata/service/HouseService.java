package com.realestate.housedata.service;

import java.util.Date;
import java.util.List;

import com.realestate.housedata.model.PropertySale;

public interface HouseService {
	void saveHouse(PropertySale House);

	List<PropertySale> findAllHousesByCity(String city, Date date);

	List<PropertySale> findAllHousesByCityHood(String city, String hood, Date date);

	PropertySale findByUrl(String url);

	void deleteHouseBySsn(String ssn);

	PropertySale findById(String ssn);

	void updateHouse(PropertySale House);
}

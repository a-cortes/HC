package com.realestate.housedata.dao;

import java.util.Date;
import java.util.List;

import com.realestate.housedata.model.PropertySale;

public interface HouseDao {
	void saveHouse(PropertySale House);

	List<PropertySale> findAllHousesbyHood(String city, String hood, Date date);

	List<PropertySale> findAllHousesbyCity(String city, Date date);

	void deleteHouseById(String ID);

	PropertySale findById(String ssn);

	PropertySale findByUrl(String url);

	void updateHouse(PropertySale House);

}

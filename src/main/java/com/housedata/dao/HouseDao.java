package com.housedata.dao;

import java.util.Date;
import java.util.List;

import com.housedata.model.House;

public interface HouseDao {
	void saveHouse(House House);

	List<House> findAllHousesbyHood(String city, String hood, Date date);

	List<House> findAllHousesbyCity(String city, Date date);

	void deleteHouseById(String ID);

	House findById(String ssn);

	House findByUrl(String url);

	void updateHouse(House House);

}

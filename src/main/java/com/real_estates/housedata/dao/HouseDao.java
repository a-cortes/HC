package com.real_estates.housedata.dao;

import com.real_estates.housedata.model.House;

import java.util.Date;
import java.util.List;

public interface HouseDao {
	void saveHouse(House House);

	List<House> findAllHousesbyHood(String city, String hood, Date date);

	List<House> findAllHousesbyCity(String city, Date date);

	void deleteHouseById(String ID);

	House findById(String ssn);

	House findByUrl(String url);

	void updateHouse(House House);

}

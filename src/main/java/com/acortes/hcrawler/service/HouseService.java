package com.acortes.hcrawler.service;

import java.util.Date;
import java.util.List;

import com.acortes.hcrawler.model.House;

public interface HouseService {
	void saveHouse(House House);

	List<House> findAllHousesByCity(String city, Date date);

	List<House> findAllHousesByCityHood(String city, String hood, Date date);

	House findByUrl(String url);

	void deleteHouseBySsn(String ssn);

	House findById(String ssn);

	void updateHouse(House House);
}

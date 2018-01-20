package com.housedata.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housedata.dao.HouseDao;
import com.housedata.model.House;

@Service("houseService")
@Transactional
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseDao dao;

	@Override
	public void saveHouse(House house) {
		dao.saveHouse(house);

	}

	@Override
	public void deleteHouseBySsn(String ssn) {
		// TODO Auto-generated method stub

	}

	@Override
	public House findById(String ssn) {
		return dao.findById(ssn);
	}

	@Override
	public void updateHouse(House House) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<House> findAllHousesByCity(String city, Date date) {

		return dao.findAllHousesbyCity(city, date);
	}

	@Override
	public List<House> findAllHousesByCityHood(String city, String hood, Date date) {
		return dao.findAllHousesbyHood(city, hood, date);
	}

	@Override
	public House findByUrl(String url) {
		return dao.findByUrl(url);
	}

}

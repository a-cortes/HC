package com.realestate.housedata.service;

import java.util.Date;
import java.util.List;

import com.realestate.housedata.dao.HouseDao;
import com.realestate.housedata.model.PropertySale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("houseService")
@Transactional
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseDao dao;

	@Override
	public void saveHouse(PropertySale house) {
		dao.saveHouse(house);

	}

	@Override
	public void deleteHouseBySsn(String ssn) {
		// TODO Auto-generated method stub

	}

	@Override
	public PropertySale findById(String ssn) {
		return dao.findById(ssn);
	}

	@Override
	public void updateHouse(PropertySale House) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PropertySale> findAllHousesByCity(String city, Date date) {

		return dao.findAllHousesbyCity(city, date);
	}

	@Override
	public List<PropertySale> findAllHousesByCityHood(String city, String hood, Date date) {
		return dao.findAllHousesbyHood(city, hood, date);
	}

	@Override
	public PropertySale findByUrl(String url) {
		return dao.findByUrl(url);
	}

}

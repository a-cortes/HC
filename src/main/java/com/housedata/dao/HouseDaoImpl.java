package com.housedata.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.housedata.model.House;

@Repository("houseDao")
public class HouseDaoImpl extends AbstractDao implements HouseDao {

	@Override
	public void saveHouse(House house) {
		persist(house);

	}

	@Override
	public void deleteHouseById(String ID) {
		// TODO Auto-generated method stub

	}

	@Override
	public House findById(String urlDate) {
		Criteria criteria = getSession().createCriteria(House.class);
		criteria.add(Restrictions.eq("urlDate", urlDate));
		return (House) criteria.uniqueResult();
	}

	@Override
	public void updateHouse(House House) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<House> findAllHousesbyHood(String city, String hood, Date date) {
		Criteria criteria = getSession().createCriteria(House.class);
		criteria.add(Restrictions.eq("ciudad", city));
		criteria.add(Restrictions.eq("fechaRegistro", date));
		criteria.add(Restrictions.eq("colonia", hood));
		return (List<House>) criteria.list();
	}

	@Override
	public List<House> findAllHousesbyCity(String city, Date date) {
		Criteria criteria = getSession().createCriteria(House.class);
		criteria.add(Restrictions.eq("ciudad", city));
		criteria.add(Restrictions.eq("fechaRegistro", date));
		return (List<House>) criteria.list();
	}

	@Override
	public House findByUrl(String url) {
		Criteria criteria = getSession().createCriteria(House.class);
		criteria.add(Restrictions.eq("source", url));
		return (House) criteria.uniqueResult();

	}

}

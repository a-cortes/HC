package com.realestate.housedata.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.realestate.housedata.model.PropertySale;

@Repository("houseDao")
public class HouseDaoImpl extends AbstractDao implements HouseDao {

	@Override
	public void saveHouse(PropertySale house) {
		persist(house);

	}

	@Override
	public void deleteHouseById(String ID) {
		// TODO Auto-generated method stub

	}

	@Override
	public PropertySale findById(String urlDate) {
		Criteria criteria = getSession().createCriteria(PropertySale.class);
		criteria.add(Restrictions.eq("urlDate", urlDate));
		return (PropertySale) criteria.uniqueResult();
	}

	@Override
	public void updateHouse(PropertySale House) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PropertySale> findAllHousesbyHood(String city, String hood, Date date) {
		Criteria criteria = getSession().createCriteria(PropertySale.class);
		criteria.add(Restrictions.eq("city", city));
		criteria.add(Restrictions.eq("registerDate", date));
		criteria.add(Restrictions.eq("hood", hood));
		return (List<PropertySale>) criteria.list();
	}

	@Override
	public List<PropertySale> findAllHousesbyCity(String city, Date date) {
		Criteria criteria = getSession().createCriteria(PropertySale.class);
		criteria.add(Restrictions.eq("city", city));
		criteria.add(Restrictions.eq("registerDate", date));
		return (List<PropertySale>) criteria.list();
	}

	@Override
	public PropertySale findByUrl(String url) {
		Criteria criteria = getSession().createCriteria(PropertySale.class);
		criteria.add(Restrictions.eq("source", url));
		return (PropertySale) criteria.uniqueResult();

	}

}

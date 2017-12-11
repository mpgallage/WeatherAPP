package com.oddle.app.dao;

import com.oddle.app.model.City;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * @author : maal (Malaka Gallage)
 * @since : 6/12/17
 */
public class CityDAOImpl implements CityDAO {

    @Autowired(required=true)
    @Qualifier(value="sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public boolean addCity(City city) {

        if (getCityByName(city.getName()) == null) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(city);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateCity(City city) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(city);
    }

    @Override
    public List<City> listCities() {
        Session session = this.sessionFactory.getCurrentSession();
        List<City> cityList = session.createQuery("from City").list();

        return cityList;
    }

    @Override
    public City getCityByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from City where name=:name");
        query.setParameter("name", name);
        List list = query.list();

        if (list.size() == 1) {
            return (City) list.get(0);
        }

        return null;
    }

    @Override
    public void removeCity(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        City city = (City) session.load(City.class, new Integer(id));
        if(null != city){
            session.delete(city);
        }
    }
}

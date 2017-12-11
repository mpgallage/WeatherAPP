package com.oddle.app.service;

import com.oddle.app.dao.CityDAO;
import com.oddle.app.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : maal (Malaka Gallage)
 * @since : 6/12/17
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired(required = true)
    @Qualifier(value = "cityDAO")
    private CityDAO cityDAO;

    @Override
    @Transactional
    public boolean addCity(City city) {
        return cityDAO.addCity(city);
    }

    @Override
    @Transactional
    public void updateCity(City city) {
        cityDAO.updateCity(city);
    }

    @Override
    @Transactional
    public List<City> listCities() {
        return cityDAO.listCities();
    }

    @Override
    @Transactional
    public City getCityByName(String name) {
        return cityDAO.getCityByName(name);
    }

    @Override
    @Transactional
    public void removeCity(int id) {
        cityDAO.removeCity(id);
    }

    public void setCityDAO(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }
}

package com.oddle.app.dao;

import com.oddle.app.model.City;

import java.util.List;

/**
 * @author : maal (Malaka Gallage)
 * @since : 6/12/17
 */
public interface CityDAO {

    public boolean addCity(City p);
    public void updateCity(City p);
    public List<City> listCities();
    public City getCityByName(String name);
    public void removeCity(int id);
}

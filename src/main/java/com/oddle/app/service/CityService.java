package com.oddle.app.service;

import com.oddle.app.model.City;

import java.util.List;

/**
 * @author : maal (Malaka Gallage)
 * @since : 6/12/17
 */
public interface CityService {

    public boolean addCity(City city);
    public void updateCity(City city);
    public List<City> listCities();
    public City getCityByName(String name);
    public void removeCity(int id);
}

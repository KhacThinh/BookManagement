package com.example.AuthorManagement.service;

import com.example.AuthorManagement.model.dto.requestDto.CityRequestDto;
import com.example.AuthorManagement.model.entities.City;
import com.example.AuthorManagement.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getCities() {
        List<City> cities = cityRepository.findAll();
        return cities;
    }

    @Override
    public City getCity(Long cityId) {
        City city = cityRepository.findById(cityId).orElseThrow(() ->
                new IllegalArgumentException("Not found City with Id " + cityId));
        return city;
    }

    @Transactional
    @Override
    public City addCity(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.getName());
        return cityRepository.save(city);
    }

    @Transactional
    @Override
    public City editCity(Long cityId, CityRequestDto cityRequestDto) {
        City city = getCity(cityId);
        city.setName(cityRequestDto.getName());
        return cityRepository.save(city);
    }

    @Transactional
    @Override
    public City deleteCity(Long cityId) {
        City city = getCity(cityId);
        cityRepository.delete(city);
        return city;
    }
}

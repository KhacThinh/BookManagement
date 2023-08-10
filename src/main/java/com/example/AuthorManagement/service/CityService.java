package com.example.AuthorManagement.service;

import com.example.AuthorManagement.model.dto.requestDto.CityRequestDto;
import com.example.AuthorManagement.model.entities.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    public List<City> getCities();

    public City getCity(Long cityId);

    public City addCity(CityRequestDto cityRequestDto);

    public City editCity(Long cityId, CityRequestDto cityRequestDto);

    public City deleteCity(Long cityId);
}

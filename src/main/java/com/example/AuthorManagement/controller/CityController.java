package com.example.AuthorManagement.controller;

import com.example.AuthorManagement.model.dto.requestDto.CityRequestDto;
import com.example.AuthorManagement.model.entities.City;
import com.example.AuthorManagement.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<City>> getAll() {
        List<City> cities = cityService.getCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/findId/{CitId}")
    public ResponseEntity<City> findByIdCity(@PathVariable final Long CitId) {
        City city = cityService.getCity(CitId);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<City> saveCity(@RequestBody final CityRequestDto cityRequestDto) {
        City city = cityService.addCity(cityRequestDto);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PutMapping("/update/{CitId}")
    public ResponseEntity<City> updateCity(
            @PathVariable final Long CitId,
            @RequestBody final CityRequestDto cityRequestDto
    ) {
        City city = cityService.editCity(CitId, cityRequestDto);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{CitId}")
    public ResponseEntity<City> deleteCity(@PathVariable final Long CitId) {
        City city = cityService.deleteCity(CitId);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}

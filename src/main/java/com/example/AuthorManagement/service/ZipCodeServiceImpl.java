package com.example.AuthorManagement.service;

import com.example.AuthorManagement.model.dto.requestDto.ZipCodeRequestDto;
import com.example.AuthorManagement.model.entities.City;
import com.example.AuthorManagement.model.entities.ZipCode;
import com.example.AuthorManagement.repositories.ZipCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ZipCodeServiceImpl implements ZipCodeService {

    private final ZipCodeRepository zipCodeRepository;
    private final CityService cityService;

    @Autowired
    public ZipCodeServiceImpl(ZipCodeRepository zipCodeRepository, CityService cityService) {
        this.zipCodeRepository = zipCodeRepository;
        this.cityService = cityService;
    }

    @Override
    public List<ZipCode> getZipCodes() {
        return StreamSupport
                .stream(zipCodeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ZipCode addZipCode(ZipCodeRequestDto zipCodeRequestDto) {
        ZipCode zipCode = new ZipCode();
        zipCode.setName(zipCodeRequestDto.getName());
        if (zipCodeRequestDto.getCityId() == null) {
            return zipCodeRepository.save(zipCode);
        }
        City city = cityService.getCity(zipCodeRequestDto.getCityId());
        zipCode.setCity(city);
        return zipCodeRepository.save(zipCode);
    }

    @Override
    public ZipCode getZipCode(Long zipCodeId) {
        ZipCode zipCode = zipCodeRepository.findById(zipCodeId).orElseThrow(() ->
                new IllegalArgumentException("Not found zipCode with id " + zipCodeId + "!")
        );
        return zipCode;
    }

    @Transactional
    @Override
    public ZipCode editZipCode(Long zipCodeId, ZipCodeRequestDto zipCodeRequestDto) {
        ZipCode zipCodeToEdit = getZipCode(zipCodeId);
        zipCodeToEdit.setName(zipCodeRequestDto.getName());
        if (zipCodeRequestDto.getCityId() == null) {
            return zipCodeToEdit;
        }
        City city = cityService.getCity(zipCodeRequestDto.getCityId());
        zipCodeToEdit.setCity(city);
        return zipCodeToEdit;
    }

    @Transactional
    @Override
    public ZipCode deleteZipCode(Long zipCodeId) {
        ZipCode zipCode = removeCityFromZipCode(zipCodeId);
        zipCodeRepository.delete(zipCode);
        return zipCode;
    }

    @Transactional
    @Override
    public ZipCode addCityToZipCode(Long zipCodeId, Long cityId) {
        ZipCode zipCode = getZipCode(zipCodeId);
        City city = cityService.getCity(cityId);
        if (zipCode.getCity() == city) {
            throw new IllegalArgumentException("City already in Zipcode");
        }
        zipCode.setCity(city);
        zipCodeRepository.save(zipCode);
        return zipCode;
    }

    @Override
    public ZipCode removeCityFromZipCode(Long zipCodeId) {
        ZipCode zipCode = getZipCode(zipCodeId);
        zipCode.setCity(null);
        zipCodeRepository.save(zipCode);
        return zipCode;
    }
}

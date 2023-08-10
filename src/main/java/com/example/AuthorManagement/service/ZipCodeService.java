package com.example.AuthorManagement.service;

import com.example.AuthorManagement.model.dto.requestDto.ZipCodeRequestDto;
import com.example.AuthorManagement.model.entities.ZipCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ZipCodeService {
    public List<ZipCode> getZipCodes();

    public ZipCode getZipCode(Long zipCodeId);

    public ZipCode addZipCode(ZipCodeRequestDto zipCodeRequestDto);

    public ZipCode editZipCode(Long zipCodeId, ZipCodeRequestDto zipCodeRequestDto);

    public ZipCode deleteZipCode(Long zipCodeId);

    public ZipCode addCityToZipCode(Long zipCodeId, Long cityId);

    public ZipCode removeCityFromZipCode(Long zipCodeId);
}

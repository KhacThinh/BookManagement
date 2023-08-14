package com.example.AuthorManagement.controller;

import com.example.AuthorManagement.model.dto.requestDto.ZipCodeRequestDto;
import com.example.AuthorManagement.model.entities.ZipCode;
import com.example.AuthorManagement.service.ZipCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipCode")
public class ZipcodeController {
    private final ZipCodeService zipCodeService;

    @Autowired
    public ZipcodeController(ZipCodeService zipCodeService) {
        this.zipCodeService = zipCodeService;
    }

    @GetMapping("")
    public ResponseEntity<List<ZipCode>> findByAll() {
        List<ZipCode> zipCode = zipCodeService.getZipCodes();
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }


    @GetMapping("/findById/{idZipCode}")
    public ResponseEntity<ZipCode> findByIdZipCode(
            @PathVariable final Long idZipCode) {
        ZipCode zipCode = zipCodeService.getZipCode(idZipCode);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ZipCode> saveZipCode(
            @RequestBody final ZipCodeRequestDto zipCodeRequestDto) {
        ZipCode zipCode = zipCodeService.addZipCode(zipCodeRequestDto);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @PutMapping("/edit/{idZipCode}")
    public ResponseEntity<ZipCode> editZipCode(
            @PathVariable final Long idZipCode,
            @RequestBody final ZipCodeRequestDto zipCodeRequestDto
    ) {
        ZipCode zipCode = zipCodeService.editZipCode(idZipCode, zipCodeRequestDto);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idZipCode}")
    public ResponseEntity<ZipCode> editZipCode(
            @PathVariable final Long idZipCode) {
        ZipCode zipCode = zipCodeService.deleteZipCode(idZipCode);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @PostMapping("/addCityToZipCode")
    public ResponseEntity<ZipCode> addCityToZipCode(
            @RequestParam(value = "idZipCode") final Long idZipCode,
            @RequestParam(value = "idCity") final Long idCity
    ) {
        ZipCode zipCode = zipCodeService.addCityToZipCode(idZipCode, idCity);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }

    @PostMapping("/removeCityFromZipCode/{idZipCode}")
    public ResponseEntity<ZipCode> removeCityFromZipCode(
            @PathVariable final Long idZipCode
    ) {
        ZipCode zipCode = zipCodeService.removeCityFromZipCode(idZipCode);
        return new ResponseEntity<>(zipCode, HttpStatus.OK);
    }
}

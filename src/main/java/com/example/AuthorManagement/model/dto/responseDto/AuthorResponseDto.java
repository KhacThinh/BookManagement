package com.example.AuthorManagement.model.dto.responseDto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AuthorResponseDto {
    private Long id;
    private String name;
    private boolean gender;
    private String address;
    private Date birthDate;
    private String biography;
    private List<String> bookNames;
    private String zipCodeName;
}

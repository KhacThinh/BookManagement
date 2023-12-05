package com.example.AuthorManagement.model.dto.requestDto;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorRequestDto {
    private String name;
    private Boolean gender;
    private String address;
    private Date birthDate;
    private String bioGraphy;
    private Long zipCodeId;
}

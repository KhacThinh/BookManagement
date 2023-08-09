package com.example.AuthorManagement.model.dto.responseDto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BookResponseDto {
    private Long id;
    private String name;
    private Date publicationDate;
    private Integer price;
    private List<String> authorNames;
    private String categoryName;
}

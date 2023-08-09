package com.example.AuthorManagement.model.dto.requestDto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BookRequestDto {
    private String name;
    private Date publicationDate;
    private Integer price;
    private List<Long> authorIds;
    private Long categoryId;
}

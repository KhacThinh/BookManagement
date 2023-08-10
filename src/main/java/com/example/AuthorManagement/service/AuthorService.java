package com.example.AuthorManagement.service;

import com.example.AuthorManagement.model.dto.requestDto.AuthorRequestDto;
import com.example.AuthorManagement.model.dto.responseDto.AuthorResponseDto;
import com.example.AuthorManagement.model.entities.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    public List<AuthorResponseDto> getAuthors();

    public Author getAuthor(Long authorId);

    public AuthorResponseDto getAuthorById(Long authorId);

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);

    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto);

    public AuthorResponseDto deleteAuthor(Long authorId);

    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipCodeId);

    public AuthorResponseDto removeZipcodeFromAuthor(Long authorId, Long zipCodeId);
}

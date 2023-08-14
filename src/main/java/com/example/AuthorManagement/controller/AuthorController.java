package com.example.AuthorManagement.controller;

import com.example.AuthorManagement.model.dto.requestDto.AuthorRequestDto;
import com.example.AuthorManagement.model.dto.responseDto.AuthorResponseDto;
import com.example.AuthorManagement.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<AuthorResponseDto>> getAll() {
        List<AuthorResponseDto> authorResponseDto = authorService.getAuthors();
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<AuthorResponseDto> saveAuthor(
            @RequestBody final AuthorRequestDto authorRequestDto) {
        AuthorResponseDto authorResponseDto = authorService.addAuthor(authorRequestDto);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorResponseDto> editAuthor(
            @PathVariable final Long authorId,
            @RequestBody final AuthorRequestDto authorRequestDto) {
        AuthorResponseDto authorResponseDto = authorService.editAuthor(authorId, authorRequestDto);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<AuthorResponseDto> deleteAuthor(
            @PathVariable final Long authorId) {
        AuthorResponseDto authorResponseDto = authorService.deleteAuthor(authorId);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @GetMapping("/find/{authorId}")
    public ResponseEntity<AuthorResponseDto> findByIdAuthor(
            @PathVariable final Long authorId) {
        AuthorResponseDto authorResponseDto = authorService.getAuthorById(authorId);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addZipCodeToAuthor")
    public ResponseEntity<AuthorResponseDto> addZipCodeToAuthor(
            @RequestParam final Long authorId,
            @RequestParam final Long zipCodeId
    ) {
        AuthorResponseDto authorResponseDto = authorService.addZipcodeToAuthor(authorId, zipCodeId);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @PostMapping("/removeZipCodeFromAuthor/{authorId}")
    public ResponseEntity<AuthorResponseDto> addZipCodeToAuthor(
            @PathVariable final Long authorId
    ) {
        AuthorResponseDto authorResponseDto = authorService.removeZipcodeFromAuthor(authorId);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }
}

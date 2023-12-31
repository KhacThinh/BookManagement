package com.example.AuthorManagement.service.impl;

import com.example.AuthorManagement.model.dto.mapper;
import com.example.AuthorManagement.model.dto.requestDto.AuthorRequestDto;
import com.example.AuthorManagement.model.dto.responseDto.AuthorResponseDto;
import com.example.AuthorManagement.model.entities.Author;
import com.example.AuthorManagement.model.entities.ZipCode;
import com.example.AuthorManagement.repositories.AuthorRepository;
import com.example.AuthorManagement.service.AuthorService;
import com.example.AuthorManagement.service.ZipCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ZipCodeService zipCodeService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ZipCodeService zipCodeService) {
        this.authorRepository = authorRepository;
        this.zipCodeService = zipCodeService;
    }

    @Override
    public List<AuthorResponseDto> getAuthors() {
        List<Author> authors = StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.authorsToAuthorsResponseDto(authors);
    }

    @Override
    public Author getAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Not found author with id " + authorId + "!"));
        return author;
    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {
        Author author = getAuthor(authorId);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setName(authorRequestDto.getName());
        author.setGender(authorRequestDto.getGender());
        author.setAddress(authorRequestDto.getAddress());
        author.setBirthDate(authorRequestDto.getBirthDate());
        author.setBiography(authorRequestDto.getBioGraphy());
        if (Objects.isNull(zipCodeService.getZipCode(authorRequestDto.getZipCodeId()))) {
            throw new IllegalArgumentException("author need a zipcode!");
        }
        ZipCode zipCode = zipCodeService.getZipCode(authorRequestDto.getZipCodeId());
        author.setZipCode(zipCode);
        authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto) {
        Author author = getAuthor(authorId);
        author.setName(authorRequestDto.getName());
        author.setGender(authorRequestDto.getGender());
        author.setAddress(authorRequestDto.getAddress());
        author.setBirthDate(authorRequestDto.getBirthDate());
        author.setBiography(authorRequestDto.getBioGraphy());
        if (Objects.nonNull(authorRequestDto.getZipCodeId())) {
            ZipCode zipCode = zipCodeService.getZipCode(authorRequestDto.getZipCodeId());
            author.setZipCode(zipCode);
        }
        authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public AuthorResponseDto patchAuthor(Long authorId, AuthorRequestDto authorRequestDto) {
        Author author = getAuthor(authorId);
        Optional.ofNullable(authorRequestDto.getName()).ifPresent(author::setName);
        Optional.ofNullable(authorRequestDto.getGender())
                .filter(gender -> gender != author.getGender())
                .ifPresent(author::setGender);
        Optional.ofNullable(authorRequestDto.getAddress()).ifPresent(author::setAddress);
        Optional.ofNullable(authorRequestDto.getBirthDate()).ifPresent(author::setBirthDate);
        Optional.ofNullable(authorRequestDto.getZipCodeId())
                .map(zipCodeService::getZipCode)
                .ifPresent(author::setZipCode);
        Optional.ofNullable(authorRequestDto.getBioGraphy()).ifPresent(author::setBiography);
        authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto deleteAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipCode(null);
        authorRepository.delete(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipCodeId) {
        Author author = getAuthor(authorId);
        ZipCode zipCode = zipCodeService.getZipCode(zipCodeId);
        if (Objects.nonNull(author.getZipCode())) {
            throw new IllegalArgumentException("author already has a zipCode");
        }
        author.setZipCode(zipCode);
        authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto removeZipcodeFromAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipCode(null);
        authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(author);
    }
}

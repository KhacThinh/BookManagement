package com.example.AuthorManagement.service;

import com.example.AuthorManagement.model.dto.requestDto.BookRequestDto;
import com.example.AuthorManagement.model.dto.responseDto.BookResponseDto;
import com.example.AuthorManagement.model.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public List<BookResponseDto> getBooks();

    public Book getBook(Long bookId);

    public BookResponseDto getBookById(Long bookId);

    public BookResponseDto addBook(BookRequestDto bookRequestDto);

    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto);

    public BookResponseDto patchBook(Long bookId, BookRequestDto bookRequestDto);

    public BookResponseDto deleteBook(Long bookId);

    public BookResponseDto addAuthorToBook(Long bookId, Long authorId);

    public BookResponseDto removeAuthorFromBook(Long bookId, Long authorId);

    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId);

    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId);

}

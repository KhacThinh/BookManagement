package com.example.AuthorManagement.controller;

import com.example.AuthorManagement.model.dto.requestDto.BookRequestDto;
import com.example.AuthorManagement.model.dto.responseDto.BookResponseDto;
import com.example.AuthorManagement.service.BookService;
import io.swagger.models.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("")
    public ResponseEntity<List<BookResponseDto>> getBooks() {
        List<BookResponseDto> bookResponseDtos = bookService.getBooks();
        return ResponseEntity.ok(bookResponseDtos);
    }

    @PostMapping("")
    public ResponseEntity<BookResponseDto> saveBook(
            @RequestBody @Valid final BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.addBook(bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> editBook(
            @PathVariable final Long bookId,
            @RequestBody @Valid final BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.editBook(bookId, bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> patchBook(
            @PathVariable final Long bookId,
            @RequestBody @Valid final BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.patchBook(bookId, bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> deleteBook(
            @PathVariable final Long bookId) {
        BookResponseDto bookResponseDto = bookService.deleteBook(bookId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addAuthorToBook")
    public ResponseEntity<BookResponseDto> addAuthorToBook(
            @RequestParam final Long bookId,
            @RequestParam final Long authorId) {
        BookResponseDto bookResponseDto = bookService.addAuthorToBook(bookId, authorId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/removeAuthorToBook")
    public ResponseEntity<BookResponseDto> removeAuthorFromBook(
            @RequestParam final Long bookId,
            @RequestParam final Long authorId) {
        BookResponseDto bookResponseDto = bookService.removeAuthorFromBook(bookId, authorId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addCategoryToBook")
    public ResponseEntity<BookResponseDto> addCategoryToBook(
            @RequestParam final Long bookId,
            @RequestParam final Long categoryId) {
        BookResponseDto bookResponseDto = bookService.addCategoryToBook(bookId, categoryId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/removeCategoryFromBook")
    public ResponseEntity<BookResponseDto> removeCategoryToBook(
            @RequestParam final Long bookId,
            @RequestParam final Long categoryId) {
        BookResponseDto bookResponseDto = bookService.removeCategoryFromBook(bookId, categoryId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
}

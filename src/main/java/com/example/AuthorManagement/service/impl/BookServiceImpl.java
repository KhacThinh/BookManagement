package com.example.AuthorManagement.service.impl;

import com.example.AuthorManagement.model.dto.mapper;
import com.example.AuthorManagement.model.dto.requestDto.BookRequestDto;
import com.example.AuthorManagement.model.dto.responseDto.BookResponseDto;
import com.example.AuthorManagement.model.entities.Author;
import com.example.AuthorManagement.model.entities.Book;
import com.example.AuthorManagement.model.entities.Category;
import com.example.AuthorManagement.repositories.BookRepository;
import com.example.AuthorManagement.service.AuthorService;
import com.example.AuthorManagement.service.BookService;
import com.example.AuthorManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
class BookServiceImpl implements BookService {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(CategoryService categoryService, AuthorService authorService, BookRepository bookRepository) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookResponseDto> getBooks() {
        List<Book> books = StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.booksToBooksResponseDto(books);
    }

    @Override
    public Book getBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Not found book with id " + bookId));
        return book;
    }

    @Override
    public BookResponseDto getBookById(Long bookId) {
        Book book = getBook(bookId);
        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setName(bookRequestDto.getName());
        book.setPrice(bookRequestDto.getPrice());
        book.setPublicationDate(bookRequestDto.getPublicationDate());
        if (bookRequestDto.getAuthorIds().isEmpty()) {
            throw new IllegalArgumentException("book need an author");
        } else {
            List<Author> authors = bookRequestDto.getAuthorIds()
                    .stream()
                    .map(authorService::getAuthor)
                    .collect(Collectors.toList());
            book.setAuthors(authors);
        }
        if (Objects.isNull(bookRequestDto.getCategoryId())) {
            throw new IllegalArgumentException("book need a category");
        }
        Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
        book.setCategory(category);
        bookRepository.save(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto) {
        Book book = getBook(bookId);
        book.setName(bookRequestDto.getName());
        book.setPrice(bookRequestDto.getPrice());
        book.setPublicationDate(bookRequestDto.getPublicationDate());
        if (bookRequestDto.getCategoryId() != null) {
            Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
            book.setCategory(category);
        }
        if (bookRequestDto.getAuthorIds().isEmpty()) {
            throw new IllegalArgumentException("book need a author");
        } else {
            List<Author> authors = new ArrayList<>();
            for (Long authorId : bookRequestDto.getAuthorIds()) {
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);
        }
        bookRepository.save(book);
        return mapper.bookToBookResponseDto(book);

    }

    @Override
    public BookResponseDto patchBook(Long bookId, BookRequestDto bookRequestDto) {
        Book book = getBook(bookId);
        Optional.ofNullable(bookRequestDto.getName()).ifPresent(book::setName);
        Optional.ofNullable(bookRequestDto.getPrice()).ifPresent(book::setPrice);
        Optional.ofNullable(bookRequestDto.getPublicationDate()).ifPresent(book::setPublicationDate);
        Optional.ofNullable(bookRequestDto.getCategoryId())
                .map(categoryService::getCategory)
                .ifPresent(book::setCategory);
        List<Long> AuthorIds = Optional.ofNullable(bookRequestDto.getAuthorIds()).orElse(Collections.emptyList());
        if (!AuthorIds.isEmpty()) {
            book.setAuthors(bookRequestDto.getAuthorIds().stream()
                    .map(authorService::getAuthor)
                    .collect(Collectors.toList())
            );
        }
        bookRepository.save(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto deleteBook(Long bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        for (Author authors : book.getAuthors()) {
            if (authors.equals(author)) {
                throw new IllegalArgumentException("This book already has this author");
            }
        }
        book.addAuthor(author);
        bookRepository.save(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto removeAuthorFromBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        for (Author authors : book.getAuthors()) {
            if (!(authors.equals(author))) {
                throw new IllegalArgumentException("author does not have this author!");
            }
        }
        author.deleteBook(book);
        book.deleteAuthor(author);
        bookRepository.save(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (Objects.nonNull(book.getCategory())) {
            throw new IllegalArgumentException("The book already has category");
        }
        book.setCategory(category);
        category.addBook(book);
        bookRepository.save(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (Objects.isNull(book)) {
            throw new IllegalArgumentException("book does not have a category to delete");
        }
        book.setCategory(null);
        category.deleteBook(book);
        bookRepository.save(book);
        return mapper.bookToBookResponseDto(book);
    }
}

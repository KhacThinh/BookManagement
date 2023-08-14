package com.example.AuthorManagement.model.dto;

import com.example.AuthorManagement.model.dto.responseDto.AuthorResponseDto;
import com.example.AuthorManagement.model.dto.responseDto.BookResponseDto;
import com.example.AuthorManagement.model.dto.responseDto.CategoryResponseDto;
import com.example.AuthorManagement.model.entities.Author;
import com.example.AuthorManagement.model.entities.Book;
import com.example.AuthorManagement.model.entities.Category;
import com.example.AuthorManagement.model.entities.ZipCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class mapper {
    public static CategoryResponseDto categoryToCategoryResponseDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        List<String> names = new ArrayList<>();
        List<Book> books = category.getBooks();
        for (Book book : books) {
            names.add(book.getName());
        }
        categoryResponseDto.setBookNames(names);
        return categoryResponseDto;
    }

    public static List<CategoryResponseDto> categoriesToCategoriesResponseDto(List<Category> categories) {
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryResponseDtos.add(categoryToCategoryResponseDto(category));
        }
        return categoryResponseDtos;
    }

    public static BookResponseDto bookToBookResponseDto(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setName(book.getName());
        bookResponseDto.setPublicationDate(book.getPublicationDate());
        bookResponseDto.setPrice(book.getPrice());
        List<String> names = new ArrayList<>();
        List<Author> authors = book.getAuthors();
        for (Author author : authors) {
            names.add(author.getName());
        }
        bookResponseDto.setAuthorNames(names);
        if (Objects.isNull(book.getCategory())) {
            bookResponseDto.setCategoryName(null);
        } else {
            bookResponseDto.setCategoryName(book.getCategory().getName());
        }
        return bookResponseDto;
    }

    public static List<BookResponseDto> booksToBooksResponseDto(List<Book> books) {
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        for (Book book : books) {
            bookResponseDtos.add(bookToBookResponseDto(book));
        }
        return bookResponseDtos;
    }

    public static AuthorResponseDto authorToAuthorResponseDto(Author author) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setName(author.getName());
        authorResponseDto.setGender(author.isGender());
        authorResponseDto.setAddress(author.getAddress());
        authorResponseDto.setBirthDate(author.getBirthDate());
        authorResponseDto.setBiography(author.getBiography());
        ZipCode zipCode = author.getZipCode();
        List<String> names = new ArrayList<>();
        if (Objects.isNull(zipCode)) {
            authorResponseDto.setZipCodeName(null);
        } else {
            authorResponseDto.setZipCodeName(zipCode.getName());
        }
        List<Book> books = author.getBooks();
        for (Book book : books) {
            names.add(book.getName());
        }
        authorResponseDto.setBookNames(names);
        return authorResponseDto;
    }

    public static List<AuthorResponseDto> authorsToAuthorsResponseDto(List<Author> authors) {
        List<AuthorResponseDto> authorResponseDtos = new ArrayList<>();
        for (Author author : authors) {
            authorResponseDtos.add(authorToAuthorResponseDto(author));
        }
        return authorResponseDtos;
    }
}

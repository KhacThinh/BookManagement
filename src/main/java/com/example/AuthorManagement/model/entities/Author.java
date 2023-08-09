package com.example.AuthorManagement.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean gender;
    private String address;
    private Date birthDate;
    private String biography;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "zipCode_id")
    private ZipCode zipCode;

    public Author(String name, boolean gender, String address, Date birthDate, String biography, List<Book> books, ZipCode zipCode) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.birthDate = birthDate;
        this.biography = biography;
        this.books = books;
        this.zipCode = zipCode;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void deleteBook(Book book) {
        books.remove(book);
    }
}

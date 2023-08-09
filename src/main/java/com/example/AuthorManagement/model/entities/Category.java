package com.example.AuthorManagement.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public Category(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    private void addBook(Book book) {
        books.add(book);
    }

    private void deleteBook(Book book) {
        books.remove(book);
    }
}

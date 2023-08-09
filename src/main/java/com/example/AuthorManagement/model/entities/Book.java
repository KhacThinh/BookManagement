package com.example.AuthorManagement.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date publicationDate;
    private Integer price;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categories;

    public Book(String name, Date publicationDate, Integer price, List<Author> authors, Category categories) {
        this.name = name;
        this.publicationDate = publicationDate;
        this.price = price;
        this.authors = authors;
        this.categories = categories;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void deleteAuthor(Author author) {
        authors.remove(author);
    }


}

package com.example.AuthorManagement.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    private String name;

    private Boolean gender;

    @NotNull(message = "address cannot be null")
    @NotBlank(message = "address cannot be blank")
    private String address;

    @NotNull(message = "date of birth cannot be null")
    @Past(message = "date of birth cannot exceed current time")
    private Date birthDate;

    @NotNull(message = "biography cannot be null")
    @NotBlank(message = "biography cannot be blank")
    private String biography;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    @NotNull(message = "author needs zipcode!")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "zipCode_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

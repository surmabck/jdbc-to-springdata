package com.example.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by surmab on 06.04.2017.
 */
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table( name = "BOOKS")
public class Book {
    @Column(name="isbn")
    @Id
    private String isbn;
    private String name;

    public Book(String isbn, String name) {
        this.isbn = isbn;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn){
        this.isbn = isbn;
        return this;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return " Book{" +
                "isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

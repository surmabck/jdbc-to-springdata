package com.example.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by surmab on 31.05.2017.
 */
@NoArgsConstructor
@Entity(name = "RENTS")
public class Rent {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="BOOKISBN", unique= true, nullable=true, insertable=true, updatable=true)
    private Book book;
    @OneToOne
    @JoinColumn(name="USERID", unique= true, nullable=true, insertable=true, updatable=true)
    private User user;

    public Long getId() {
        return id;
    }

    public Rent setId(Long id) {
        this.id = id;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public Rent setBook(Book book) {
        this.book = book;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Rent setUser(User user) {
        this.user = user;
        return this;
    }

    public Rent(Long id) {
        this.id = id;
    }

    public Rent(Long id, Book book, User user) {
        this.id = id;
        this.book = book;
        this.user = user;
    }

    public Rent(Book book, User user) {
        this.book = book;
        this.user = user;
    }
}

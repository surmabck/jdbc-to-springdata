package com.example.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by surmab on 31.05.2017.
 */
@NoArgsConstructor
public class Rent {
    private Long id;
    private Book book;
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

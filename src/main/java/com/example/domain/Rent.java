package com.example.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by surmab on 31.05.2017.
 */
@NoArgsConstructor
@Builder
public class Rent {
    private Long id;
    private Book book;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

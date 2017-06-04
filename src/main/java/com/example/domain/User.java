package com.example.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by surmab on 06.04.2017.
 */
@NoArgsConstructor
@EqualsAndHashCode
public class User implements  Persistable<Long>{

    private Long id;
    private String userName;
    private String password;
    private List<Rent> bookRents;

    public User(Long id, String userName, String password, List<Rent> rents) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        bookRents = new ArrayList<>();
    }

    public List<Rent> getBookRents() {
        return bookRents;
    }

    public void setBookRents(List<Rent> bookRents) {
        this.bookRents = bookRents;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return  " User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

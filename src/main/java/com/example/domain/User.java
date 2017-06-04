package com.example.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by surmab on 06.04.2017.
 */
@Builder
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

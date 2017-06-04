package com.example.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by surmab on 06.04.2017.
 */
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Book implements Persistable<String> {

    private String ISBN;
    private String name;

    public Book(String ISBN, String name) {
        this.ISBN = ISBN;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String isbn){
        this.ISBN = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return ISBN;
    }

    @Override
    public String toString() {
        return " Book{" +
                "ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.example.factory;

import com.example.domain.Book;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by surmab on 06.04.2017.
 */
@Builder
@NoArgsConstructor
public class BookFactory implements DomainFactory<Book> {

    public Book create(){
        return new Book();
    }

}

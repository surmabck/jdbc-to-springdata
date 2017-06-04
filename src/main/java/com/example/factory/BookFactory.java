package com.example.factory;

import com.example.configuration.BookConfiguration;
import com.example.domain.Book;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by surmab on 06.04.2017.
 */
@Component
@Builder
@NoArgsConstructor
public class BookFactory {

    public Book createBook(){
        return new Book();
    }

}

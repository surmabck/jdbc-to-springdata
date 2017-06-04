package com.example.factory;

import com.example.domain.Book;
import com.example.domain.Rent;
import org.springframework.stereotype.Component;

/**
 * Created by surmab on 01.06.2017.
 */
@Component
public class RentFactory {
    public Rent createRent(){
        return new Rent();
    }
}

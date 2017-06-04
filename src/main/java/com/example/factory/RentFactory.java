package com.example.factory;

import com.example.domain.Rent;
import org.springframework.stereotype.Component;

/**
 * Created by surmab on 01.06.2017.
 */
public class RentFactory implements DomainFactory<Rent>{
    public Rent create(){
        return new Rent();
    }
}

package com.example.factory;

/**
 * Created by bartosz on 2017-06-04.
 */
public interface DomainFactory<T> {
    T create();
}

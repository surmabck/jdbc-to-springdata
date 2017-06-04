package com.example.repository;

import java.util.List;

/**
 * Created by surmab on 06.04.2017.
 */
interface Repository<T, U> {

    T findById(U id);

    List<T> findAll();

    boolean save(T object);

    boolean delete(T object);
}

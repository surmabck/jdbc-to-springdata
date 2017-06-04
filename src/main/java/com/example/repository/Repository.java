package com.example.repository;

import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by surmab on 06.04.2017.
 */
@Log4j2
public abstract class Repository<T, U> {

    public abstract T findById(U id);

    public abstract List<T> findAll();

    public abstract void save(T object);

    public abstract void delete(T object);
    public void logError(SQLException e) {
        logError(e,"");
    }
    public void logError(SQLException e, String query) {
        log.fatal("Query Failed : " + query, e);
    }
}

package com.example.repository.query;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by bartosz on 2017-06-04.
 */
@Log4j2
public abstract class Query<T> {
    private Connection conn = null;
    private PreparedStatement statement = null;
    private String query = null;
    private T result = null;

    public Query(Connection conn, String query) {
        this.conn = conn;
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }


    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }
    protected void prepareStatement() throws SQLException {
        statement = getConn().prepareStatement(getQuery());
    }
    protected void shutDownStatement() {
        try {
            statement.close();
        } catch (SQLException e) {
            logError(e);
        }
    }
    public void execute() {
        try {
            prepareStatement();
            preExecute();
            executeStatement();
        } catch (SQLException e) {
            logError(e,getQuery());
        } finally {
            shutDownStatement();
        }
    }

    public T getResult(){
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    protected abstract void preExecute() throws SQLException;

    protected abstract void executeStatement() throws SQLException;
    public void logError(SQLException e) {
        logError(e,"");
    }
    public void logError(SQLException e, String query) {
        log.fatal("Query Failed : " + query, e);
    }
}

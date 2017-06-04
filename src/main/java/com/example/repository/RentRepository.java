package com.example.repository;

import com.example.HsqlDatabase;
import com.example.domain.Book;
import com.example.domain.Rent;
import com.example.domain.User;
import com.example.factory.RentFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by surmab on 31.05.2017.
 */
@Log4j2
@org.springframework.stereotype.Repository
public class RentRepository implements Repository<Rent,Long> {
    private static final String INSERT_QUERY = "INSERT INTO RENTS(ID,BOOKISBN,USERID) VALUES(?, ?, ?)";
    private static final String FIND_QUERY = "SELECT RENTS.ID as ID, BOOKS.ISBN as ISBN, BOOKS.Name as NAME, USERS.ID as USERID, USERS.PASSWORD as PASSWORD, USERS.USERNAME as USERNAME FROM RENTS JOIN BOOKS ON RENTS.BOOKISBN = BOOKS.ISBN JOIN USERS ON RENTS.USERID = USERS.ID WHERE RENTS.ID = ?";
    private static final String FIND_ALL_QUERY = "SELECT RENTS.ID as ID, BOOKS.ISBN as ISBN, BOOKS.Name as NAME, USERS.ID as USERID, USERS.PASSWORD as PASSWORD, USERS.USERNAME as USERNAME FROM RENTS JOIN BOOKS ON RENTS.BOOKISBN = BOOKS.ISBN JOIN USERS ON RENTS.USERID = USERS.ID";
    private static final String DELETE_QUERY = "DELETE FROM RENTS WHERE ID=?";

    private HsqlDatabase db;
    private RentFactory rentFactory;

    @Autowired
    public RentRepository(HsqlDatabase db) {
        this.db = db;
    }

    @Override
    public Rent findById(Long id) {

        PreparedStatement st = null;
        try {
            st = db.conn.prepareStatement(FIND_QUERY);
            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();
            if(resultSet.next()){
                Long rentsId = resultSet.getLong("ID");
                String bookISBN = resultSet.getString("ISBN");
                String bookName = resultSet.getString("NAME");
                Long userId = resultSet.getLong("USERID");
                String userPassword = resultSet.getString("PASSWORD");
                String userUserName = resultSet.getString("USERNAME");
                Book book = Book.builder().ISBN(bookISBN).name(bookName).build();
                User user = User.builder().password(userPassword).userName(userUserName).id(userId).build();
                Rent rent = Rent.builder().user(user).book(book).id(rentsId).build();
                return rent;
            }

        } catch (SQLException e) {
            log.fatal("Query Failed : " + FIND_QUERY, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }

            }
        }
        return null;
    }

    @Override
    public List<Rent> findAll() {
        PreparedStatement st = null;
        List<Rent> rents = new ArrayList<>();
        try {
            st = db.conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){
                Long rentsId = resultSet.getLong("ID");
                String bookISBN = resultSet.getString("ISBN");
                String bookName = resultSet.getString("NAME");
                Long userId = resultSet.getLong("USERID");
                String userPassword = resultSet.getString("PASSWORD");
                String userUserName = resultSet.getString("USERNAME");
                Book book = Book.builder().ISBN(bookISBN).name(bookName).build();
                User user = User.builder().password(userPassword).userName(userUserName).id(userId).build();
                Rent rent = Rent.builder().user(user).book(book).id(rentsId).build();
                rents.add(rent);
            }
            return rents;

        } catch (SQLException e) {
            log.fatal("Query Failed : " + FIND_QUERY, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }

            }
        }
        return null;
    }

    @Override
    public boolean save(Rent rent) {
        PreparedStatement st = null;
        boolean ret = false;
        try {
            st = db.conn.prepareStatement(INSERT_QUERY);
            st.setLong(1, rent.getId());
            st.setString(2, rent.getBook().getISBN());
            st.setLong(3,rent.getUser().getId());
            st.execute();
            ret = true;
        } catch (SQLException e) {
            log.fatal("Query Failed : " + INSERT_QUERY, e);
            ret = false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }

            }
            return ret;
        }
    }

    @Override
    public boolean delete(Rent rent) {
        PreparedStatement st = null;
        boolean ret = false;
        try {
            st = db.conn.prepareStatement(DELETE_QUERY);
            st.setLong(1, rent.getId());
            st.execute();
            ret = true;
        } catch (SQLException e) {
            log.fatal("Query Failed : " + DELETE_QUERY, e);
            ret = false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }

            }
            return ret;
        }
    }
}

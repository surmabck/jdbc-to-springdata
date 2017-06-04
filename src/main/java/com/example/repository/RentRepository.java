package com.example.repository;

import com.example.HsqlDatabase;
import com.example.domain.Book;
import com.example.domain.Rent;
import com.example.domain.User;
import com.example.factory.BookFactory;
import com.example.factory.DomainFactory;
import com.example.factory.RentFactory;
import com.example.factory.UserFactory;
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
public class RentRepository extends Repository<Rent,Long> {
    public static final String INSERT_QUERY = "INSERT INTO RENTS(ID,BOOKISBN,USERID) VALUES(?, ?, ?)";
    public static final String FIND_QUERY = "SELECT RENTS.ID as ID, BOOKS.ISBN as ISBN, BOOKS.Name as NAME, USERS.ID as USERID, USERS.PASSWORD as PASSWORD, USERS.USERNAME as USERNAME FROM RENTS JOIN BOOKS ON RENTS.BOOKISBN = BOOKS.ISBN JOIN USERS ON RENTS.USERID = USERS.ID WHERE RENTS.ID = ?";
    public static final String FIND_ALL_QUERY = "SELECT RENTS.ID as ID, BOOKS.ISBN as ISBN, BOOKS.Name as NAME, USERS.ID as USERID, USERS.PASSWORD as PASSWORD, USERS.USERNAME as USERNAME FROM RENTS JOIN BOOKS ON RENTS.BOOKISBN = BOOKS.ISBN JOIN USERS ON RENTS.USERID = USERS.ID";
    public static final String DELETE_QUERY = "DELETE FROM RENTS WHERE ID=?";

    private HsqlDatabase db;
    private DomainFactory<Rent> rentFactory;
    private DomainFactory<User> userFactory;
    private DomainFactory<Book> bookFactory;

    public HsqlDatabase getDb() {
        return db;
    }

    public void setDb(HsqlDatabase db) {
        this.db = db;
    }
    public void setRentFactory(RentFactory rentFactory) {
        this.rentFactory = rentFactory;
    }
    public DomainFactory getUserFactory() {
        return userFactory;
    }
    public void setUserFactory(DomainFactory userFactory) {
        this.userFactory = userFactory;
    }
    public void setBookFactory(BookFactory bookFactory) {
        this.bookFactory = bookFactory;
    }

    @Autowired
    public RentRepository(HsqlDatabase db) {
        this.db = db;
        bookFactory = new BookFactory();
        rentFactory = new RentFactory();
        userFactory = new UserFactory();
    }

    @Override
    public Rent findById(Long id) {

        PreparedStatement st = null;
        Rent rent = null;
        try {
            st = db.conn.prepareStatement(FIND_QUERY);
            rent = findRentById(id, st);
        } catch (SQLException e) {
            logError(e,FIND_QUERY);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logError(e);
                }

            }
        }
        return rent;
    }

    @Override
    public List<Rent> findAll() {
        PreparedStatement st = null;
        List<Rent> allRents = null;
        try {
            st = db.conn.prepareStatement(FIND_ALL_QUERY);
            allRents = findAllRents(st);
        } catch (SQLException e) {
            logError(e,FIND_ALL_QUERY);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logError(e);
                }
            }
        }
        return allRents;
    }

    @Override
    public void save(Rent rent) {
        PreparedStatement st = null;
        try {
            st = db.conn.prepareStatement(INSERT_QUERY);
            saveRent(rent, st);
        } catch (SQLException e) {
            logError(e,INSERT_QUERY);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logError(e);
                }

            }
        }
    }

    @Override
    public void delete(Rent rent) {
        PreparedStatement st = null;
        try {
            st = db.conn.prepareStatement(DELETE_QUERY);
            deleteRent(st, rent.getId());
        } catch (SQLException e) {
            logError(e, DELETE_QUERY);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logError(e);
                }

            }
        }
    }

    private Rent findRentById(Long id, PreparedStatement st) throws SQLException {
        st.setLong(1, id);
        ResultSet resultSet = st.executeQuery();
        if(resultSet.next()){
            Rent rent = extractALlDataForRent(resultSet);
            return rent;
        }
        return null;
    }

    private List<Rent> findAllRents(PreparedStatement st) throws SQLException {
        List<Rent> rents = new ArrayList<>();
        ResultSet resultSet = st.executeQuery();
        while(resultSet.next()){
            Rent rent = extractALlDataForRent(resultSet);
            rents.add(rent);
        }
        return rents;
    }
    private Rent extractALlDataForRent(ResultSet resultSet) throws SQLException {
        Long rentsId = resultSet.getLong("ID");
        String bookISBN = resultSet.getString("ISBN");
        String bookName = resultSet.getString("NAME");
        Long userId = resultSet.getLong("USERID");
        String userPassword = resultSet.getString("PASSWORD");
        String userUserName = resultSet.getString("USERNAME");
        Book book = bookFactory.create().setISBN(bookISBN).setName(bookName);
        User user = userFactory.create().setPassword(userPassword).setUserName(userUserName).setId(userId);
        return rentFactory.create().setUser(user).setBook(book).setId(rentsId);
    }

    private void saveRent(Rent rent, PreparedStatement st) throws SQLException {
        st.setLong(1, rent.getId());
        st.setString(2, rent.getBook().getISBN());
        st.setLong(3,rent.getUser().getId());
        st.execute();
    }

    private void deleteRent(PreparedStatement st, Long id) throws SQLException {
        st.setLong(1, id);
        st.execute();
    }
}

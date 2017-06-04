package com.example.repository;

import com.example.HsqlDatabase;
import com.example.domain.User;
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
 * Created by surmab on 06.04.2017.
 */

@org.springframework.stereotype.Repository
@Log4j2
public class UserRepository extends Repository<User,Long> {
    public static final String INSERT_QUERY = "INSERT INTO USERS(ID,PASSWORD, USERNAME) VALUES(?, ?, ?)";
    public static final String FIND_QUERY = "SELECT * FROM USERS WHERE ID=?";
    public static final String FIND_ALL_QUERY = "SELECT * FROM USERS";
    public static final String DELETE_QUERY = "DELETE FROM USERS WHERE ID=?";

    private HsqlDatabase db;
    private UserFactory userFactory;

    public HsqlDatabase getDb() {
        return db;
    }

    public void setDb(HsqlDatabase db) {
        this.db = db;
    }

    public DomainFactory getUserFactory() {
        return userFactory;
    }

    public void setUserFactory(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Autowired
    public UserRepository(HsqlDatabase db, RentFactory rentFactory, UserFactory userFactory) {
        this.db = db;
        this.userFactory = userFactory;
    }

    @Override
    public User findById(Long id) {
        PreparedStatement st = null;
        User user = null;
        try {
            st = db.conn.prepareStatement(FIND_QUERY);
            user = findUserById(id, st);
        } catch (SQLException e) {
            logError(e, FIND_QUERY);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logError(e);
                }
            }
        }
        return user;
    }

    private User findUserById(Long id, PreparedStatement st) throws SQLException {
        st.setLong(1, id);
        ResultSet resultSet = st.executeQuery();
        if(resultSet.next()){
            Long userID = resultSet.getLong("ID");
            String password = resultSet.getString("PASSWORD");
            String username = resultSet.getString("USERNAME");
            User user = userFactory.create();
            user.setId(userID);
            user.setPassword(password);
            user.setUserName(username);
            return user;
        }
        st.close();
        return null;
    }

    @Override
    public List<User> findAll() {
        PreparedStatement st = null;
        List<User> allUsers = null;
        try {
            st = db.conn.prepareStatement(FIND_ALL_QUERY);
            allUsers = findAllUsers(st);
        } catch (SQLException e) {
            logError(e, FIND_ALL_QUERY);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logError(e);
                }

            }
        }
        return allUsers;
    }

    @Override
    public void save(User user) {
        PreparedStatement st = null;
        try {
            st = db.conn.prepareStatement(INSERT_QUERY);
            saveUser(user, st);
        } catch (SQLException e) {
            logError(e, INSERT_QUERY);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }

            }
        }
    }

    @Override
    public void delete(User user) {
        PreparedStatement st = null;
        try {
            st = db.conn.prepareStatement(DELETE_QUERY);
            deleteUser(user, st);
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

    private List<User> findAllUsers(PreparedStatement st) throws SQLException {
        ResultSet resultSet = st.executeQuery();
        List<User> users = new ArrayList<>();
        while(resultSet.next()){
            Long id = resultSet.getLong("ID");
            String username = resultSet.getString("USERNAME");
            String password = resultSet.getString("PASSWORD");
            User user = userFactory.create().setId(id).setUserName(username).setPassword(password);
            users.add(user);
        }
        return users;
    }

    private void saveUser(User user, PreparedStatement st) throws SQLException {
        st.setLong(1, user.getId());
        st.setString(2, user.getPassword());
        st.setString(3, user.getUserName());
        st.execute();
    }

    private void deleteUser(User user, PreparedStatement st) throws SQLException {
        st.setLong(1, user.getId());
        st.execute();
    }

}

package com.example.repository;

import com.example.HsqlDatabase;
import com.example.domain.Book;
import com.example.domain.User;
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
public class UserRepository implements Repository<User,Long> {
    private static final String INSERT_QUERY = "INSERT INTO USERS(ID,PASSWORD, USERNAME) VALUES(?, ?, ?)";
    private static final String FIND_QUERY = "SELECT * FROM USERS WHERE ID=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM USERS";
    private static final String DELETE_QUERY = "DELETE FROM USERS WHERE ID=?";

    private HsqlDatabase db;
    private UserFactory userFactory;
    @Autowired
    public UserRepository(HsqlDatabase db, RentFactory rentFactory, UserFactory userFactory) {
        this.db = db;
        this.userFactory = userFactory;
    }

    @Override
    public User findById(Long id) {

        PreparedStatement st = null;
        try {
            st = db.conn.prepareStatement(FIND_QUERY);
            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();
            if(resultSet.next()){
                String isbn = resultSet.getString("ID");
                String password = resultSet.getString("PASSWORD");
                String username = resultSet.getString("USERNAME");
                User user = userFactory.createUser();
                user.setId(id);
                user.setPassword(password);
                user.setUserName(username);
                return user;
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
    public List<User> findAll() {
        PreparedStatement st = null;
        List<User> users = new ArrayList<>();
        try {
            st = db.conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("ID");
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                User user = User.builder().id(id).userName(username).password(password).build();
                users.add(user);
            }
            return users;

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
    public boolean save(User user) {
        PreparedStatement st = null;
        boolean ret = false;
        try {
            st = db.conn.prepareStatement(INSERT_QUERY);
            st.setLong(1, user.getId());
            st.setString(2, user.getPassword());
            st.setString(3, user.getUserName());
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
    public boolean delete(User user) {
        PreparedStatement st = null;
        boolean ret = false;
        try {
            st = db.conn.prepareStatement(DELETE_QUERY);
            st.setLong(1, user.getId());
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

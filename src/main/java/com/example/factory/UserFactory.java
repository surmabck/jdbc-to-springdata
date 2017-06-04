package com.example.factory;

import com.example.configuration.UserConfiguration;
import com.example.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by surmab on 06.04.2017.
 */
@NoArgsConstructor
@Component
public class UserFactory {

    public User createUser(){
        User user = new User();
        return user;
    }
}

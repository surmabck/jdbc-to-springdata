package com.example.factory;

import com.example.domain.User;
import lombok.NoArgsConstructor;

/**
 * Created by surmab on 06.04.2017.
 */
@NoArgsConstructor
public class UserFactory implements DomainFactory<User> {

    public User create(){
        return new User();
    }
}

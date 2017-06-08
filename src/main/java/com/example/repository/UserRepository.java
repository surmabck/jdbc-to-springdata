package com.example.repository;

import com.example.domain.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by surmab on 06.04.2017.
 */

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findById(Long id);

}

package com.example.repository;

import com.example.domain.Rent;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by surmab on 31.05.2017.
 */
@org.springframework.stereotype.Repository
public interface RentRepository  extends JpaRepository<Rent,Long>{
    Rent findById(Long id);
}

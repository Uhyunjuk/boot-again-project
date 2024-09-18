package com.example.bootagain.repository;

import com.example.bootagain.entity.Coffee;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {

    // CrudRepository 메서드 오버라이딩
    @Override
    ArrayList<Coffee> findAll();

}

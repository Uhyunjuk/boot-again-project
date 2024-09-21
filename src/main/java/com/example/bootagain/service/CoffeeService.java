package com.example.bootagain.service;

import com.example.bootagain.dto.CoffeeDto;
import com.example.bootagain.entity.Coffee;
import com.example.bootagain.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;


    // GET
    public Iterable<Coffee> index() {
        return coffeeRepository.findAll();
    }
    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    // POST
    public Coffee create(CoffeeDto coffeeDto) {
        Coffee coffee = coffeeDto.toEntity();
        // 데이터 변경되지 않게 하기
        if (coffee.getId() != null) {
            return null;
        }
        return coffeeRepository.save(coffee);
    }

    // PATCH
    public Coffee update(Long id, CoffeeDto coffeeDto) {
        // 1. dto->entity
        Coffee coffee = coffeeDto.toEntity();
        log.info("id: {}, coffee: {}", id, coffee.toString());

        // 2. target 조회
        Coffee target = coffeeRepository.findById(id).orElse(null);

        // 3. 예외처리
        if (target == null || id != coffee.getId()) {
            log.info("잘못된 요청입니다! id: {}, coffee: {}", id, coffee.toString());
            return null;
        }

        // 4. 업데이트 및 응답해주기
        target.patch(coffee); // 기존 데이터에 새 데이터 붙이기
        Coffee updated = coffeeRepository.save(target);
        return updated; // 최종 수정 데이터 반환
    }

    // DELETE
    public Coffee delete(Long id) {
        // 1. 삭제할 대상 선택
        Coffee target = coffeeRepository.findById(id).orElse(null);

        // 2. 예외처리
        if (target == null) {
            return null;
        }

        // 3. 대상 삭제
        coffeeRepository.delete(target);
        return target; // 삭제한 데이터 반환
    }
}
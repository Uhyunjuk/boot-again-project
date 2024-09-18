package com.example.bootagain.api;

import com.example.bootagain.dto.CoffeeDto;
import com.example.bootagain.entity.Coffee;
import com.example.bootagain.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CoffeeApiController {

    @Autowired
    CoffeeRepository coffeeRepository;

    // GET
    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/api/coffees/{id}")
    public Coffee Show(@PathVariable Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeDto coffeeDto) {
        Coffee entity = coffeeDto.toEntity();
        return coffeeRepository.save(entity);
    }

    // PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id,
                                         @RequestBody CoffeeDto coffeeDto) {
        // 1. dto->entity
        Coffee coffeeEntity = coffeeDto.toEntity();
        log.info("id: {}, coffee: {}", id, coffeeEntity.toString());

        // 2. target 조회
        Coffee target = coffeeRepository.findById(id).orElse(null);

        // 3. 예외처리
        if (target == null || id != coffeeEntity.getId()) {
            log.info("잘못된 요청입니다! id: {}, coffee: {}", id, coffeeEntity.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 업데이트 및 응답해주기
        target.patch(coffeeEntity); // 새 데이터 + 기존 데이터
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        // 1. 삭제할 대상 선택
        Coffee target = coffeeRepository.findById(id).orElse(null);

        // 2. 예외처리
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 3. 대상 삭제
        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

package com.example.bootagain.api;

import com.example.bootagain.dto.CoffeeDto;
import com.example.bootagain.entity.Coffee;
import com.example.bootagain.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class CoffeeApiController {

    @Autowired
    private CoffeeService coffeeService;

    // GET
    @GetMapping("/api/coffees")
    public Iterable<Coffee> index() {
        return coffeeService.index();
    }
    @GetMapping("/api/coffees/{id}")
    public Coffee Show(@PathVariable Long id) {
        return coffeeService.show(id);
    }

    // POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto coffeeDto) {
        Coffee createdCoffee = coffeeService.create(coffeeDto);
        return (createdCoffee != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdCoffee) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }

    // PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id,
                                         @RequestBody CoffeeDto coffeeDto) {

        Coffee updated = coffeeService.update(id, coffeeDto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee deleted = coffeeService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

package com.example.bootagain.dto;

import com.example.bootagain.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class CoffeeDto {

    private Long id;
    private String name;
    private String price;

    // dto -> entity
    public Coffee toEntity() {
        return new Coffee(id, name, price);

    }
}

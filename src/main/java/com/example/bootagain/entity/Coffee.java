package com.example.bootagain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String price;

    public void patch(Coffee coffeeEntity) {
        if (coffeeEntity.name != null) {
            this.name = coffeeEntity.name;
        }
        if (coffeeEntity.price != null) {
            this.price = coffeeEntity.price;
        }
    }
}

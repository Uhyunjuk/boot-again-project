package com.example.bootagain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;
    @Column
    private String password;

}

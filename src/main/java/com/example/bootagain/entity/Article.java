package com.example.bootagain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
public class Article {

    @Id // 엔티티 대표값
    @GeneratedValue // 대표값 자동 생성
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

}
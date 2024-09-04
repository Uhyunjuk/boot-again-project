package com.example.bootagain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor
@ToString
public class Article {

    @Id // 엔티티 대표값
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동생성
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

}
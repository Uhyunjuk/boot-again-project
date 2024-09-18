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

    // 수정할 내용이 있는 경우에만 동작하면 됨
    public void patch(Article dtoEntity) {
        if (dtoEntity.title != null) {
            this.title = dtoEntity.title;
        }
        if (dtoEntity.content != null) {
            this.content = dtoEntity.content;
        }
    }
}
package com.example.bootagain.dto;

import com.example.bootagain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 생성자 자동 생성
@ToString
public class ArticleForm {
    // 필드
    private String title;
    private String content;

    // DTO인 form 객체를 엔티티 객체로 변환하는 역할
    public Article toEntity() {
        return new Article(null, title, content); // 전달받은 Article 클래스의 생성자 형태로 적기
    }
}

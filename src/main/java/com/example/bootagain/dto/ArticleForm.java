package com.example.bootagain.dto;

import com.example.bootagain.entity.Article;
import lombok.*;

@AllArgsConstructor // 생성자 자동 생성
@ToString
public class ArticleForm {
    // 필드
    private Long id; // 수정 폼에서 hidden된 id 추가했으므로 id 필드 추가
    private String title;
    private String content;


    // DTO인 form 객체를 엔티티 객체로 변환하는 역할
    public Article toEntity() {
        return new Article(id, title, content); // null->id로 수정
    }
}

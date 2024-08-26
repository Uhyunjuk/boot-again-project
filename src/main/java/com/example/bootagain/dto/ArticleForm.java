package com.example.bootagain.dto;

import com.example.bootagain.entity.Article;

public class ArticleForm {
    // 필드
    private String title;
    private String content;

    // 생성자(전송받은 title과 content를 필드에 저장)
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 데이터를 잘 받았는지 확인할 toString()
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    // DTO인 form 객체를 엔티티 객체로 변환하는 역할
    public Article toEntity() {
        return new Article(null, title, content); // 전달받은 Article클래스(엔티티)의 생성자 형태로 적어주면 됨
    }
}

package com.example.bootagain.controller;

import com.example.bootagain.dto.ArticleForm;
import com.example.bootagain.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private ArticleRepository articleRepository;

    // 사용자에게 폼 데이터 입력 받고 보내는 페이지
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    // 폼 데이터 받고 DTO에 담기
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { // ArticleForm 타입의 form 객체를 매개변수로 선언
        System.out.println(form.toString());

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity(); // form 객체의 toEntity()를 호출해서 반환값을 Article 타입의 article 엔티티에 저장

        // 2. 레파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환

        return "";
    }


}

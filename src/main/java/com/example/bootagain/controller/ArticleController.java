package com.example.bootagain.controller;

import com.example.bootagain.dto.ArticleForm;
import com.example.bootagain.entity.Article;
import com.example.bootagain.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅 기능
public class ArticleController {

    @Autowired // 스프링부트가 미리 생성해놓은 repository 객체주입(DI)
    private ArticleRepository articleRepository;

    // 사용자에게 폼 데이터 입력 받고 보내는 페이지
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    // 폼 데이터 받고 DTO에 담기
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { // 전송받은 폼 데이터를 매개변수로 받음
        log.info(form.toString());

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity(); // form객체의 toEntity()를 호출해서 반환값을 article 엔티티에 저장
        log.info(article.toString());

        // 2. repository로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());

        return "";
    }


}

package com.example.bootagain.controller;

import com.example.bootagain.dto.ArticleForm;
import com.example.bootagain.entity.Article;
import com.example.bootagain.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅 기능
public class ArticleController {

    @Autowired // 스프링부트가 미리 생성해놓은 repository 객체주입(DI)
    private ArticleRepository articleRepository;

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

    // 단일 데이터 조회하기
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) { // 매개변수로 id 받아오기
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null); // 값이 없으면 null을 저장

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    // 데이터 목록 조회하기
    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지 설정하기
        return "articles/index";

    }


}

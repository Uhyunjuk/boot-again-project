package com.example.bootagain.repository;

import com.example.bootagain.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> { // JPA에서 제공하는 인터페이스

    // 오버라이딩
    @Override
    ArrayList<Article> findAll(); // Iterable -> ArrayList 수정

}

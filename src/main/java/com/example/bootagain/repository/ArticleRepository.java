package com.example.bootagain.repository;

import com.example.bootagain.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> { // JPA에서 제공하는 인터페이스



}

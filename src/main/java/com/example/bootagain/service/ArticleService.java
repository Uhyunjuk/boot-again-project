package com.example.bootagain.service;

import com.example.bootagain.dto.ArticleForm;
import com.example.bootagain.entity.Article;
import com.example.bootagain.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    // GET
    public List<Article> index() {
        return articleRepository.findAll();
    }
    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    public Article create(ArticleForm adto) {
        Article article = adto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    // PATCH
    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환
        Article dtoEntity = dto.toEntity();
        log.info("id: {}, article: {}", id, dtoEntity.toString());

        // 2. 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리
        if (target == null || id != dtoEntity.getId()) {
            // 400 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, article: {}", id, dtoEntity.toString());
            return null;
        }

        // 4. 업데이트 및 정상 응답(200)
        target.patch(dtoEntity); // 기존 데이터에 새 데이터 붙이기
        Article updated = articleRepository.save(target);
        return updated; // 수정데이터만 반환
    }

    // DELETE
    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리
        if (target == null) {
            return null;
        }

        // 3. 대상 삭제
        articleRepository.delete(target);
        return target; // DB에서 삭제한 대상을 컨트롤러에 반환
    }


    @Transactional // 해당 메서드는 하나의 트랜잭션으로 묶임
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음 -> entity 묶음
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // 2. entity 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 3. 강제로 에러 발생시키기
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패"));

        // 4. 결과 값 반환하기
        return articleList;
    }
}

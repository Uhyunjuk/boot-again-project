package com.example.bootagain.service;

import com.example.bootagain.dto.ArticleForm;
import com.example.bootagain.entity.Article;
import com.example.bootagain.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}

package com.gachon.healthdiary.API;

import com.gachon.healthdiary.DTO.ArticleForm;
import com.gachon.healthdiary.Entity.Article;
import com.gachon.healthdiary.Service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/articles")
public class ArticleAPIController {
    @Autowired
    private ArticleService articleService;

    // GET
    @GetMapping("")
    public List<Article> getArticlesList() {
        return articleService.findAllArticles();
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.findArticle(id);
    }

    // POST
    @PostMapping("")
    public ResponseEntity<Article> createArticle(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        // 생성요청 결과에 따라 다른 응답값을 반환
        return (created == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) :
                ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) :
                ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id){
        // 1. 서비스를 통해 게시글 삭제
        Article deleted = articleService.delete(id);

        // 2. 삭제 결과에 따라 응답처리
        return deleted == null ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

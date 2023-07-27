package com.gachon.healthdiary.API;

import com.gachon.healthdiary.DTO.ArticleForm;
import com.gachon.healthdiary.Entity.Article;
import com.gachon.healthdiary.Repository.ArticleRepository;
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
    ArticleRepository articleRepository;

    // GET
    @GetMapping("")
    public List<Article> getArticlesList() {
        return articleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/create")
    public Article createArticle(@RequestBody ArticleForm dto) {
        Article newArticle = dto.toEntity();
        return articleRepository.save(newArticle);
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody ArticleForm dto) {
        // 1. 변경된 내용을 담은 DTO -> Entity
        Article edited = dto.toEntity();
        log.info("id: {}, article: {}", id, edited.toString());

        // 2. 변경할 id의 객체찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청인지 확인
        if (target == null || id != edited.getId()){
            log.info("잘못된 요청! id: {}, article: {}", id, target.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 정상적인 요청 처리
        target.patch(edited);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 1. DB에 대상 Entity 존재여부 확인
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 대상 Entity가 없어 예외처리
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 3. 대상 Entity가 있어 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}

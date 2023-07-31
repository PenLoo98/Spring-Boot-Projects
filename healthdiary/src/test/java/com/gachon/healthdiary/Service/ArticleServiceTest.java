package com.gachon.healthdiary.Service;

import com.gachon.healthdiary.Entity.Article;
import com.gachon.healthdiary.Repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;

    @Test
    void findAllArticles() {
        // 1. 예상 데이터
        Article a = new Article(1L, "제목 1", "1111");
        Article b = new Article(2L, "제목 2", "2222");
        Article c = new Article(3L, "제목 3", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        expected.stream()
                .forEach(newArticle -> articleRepository.save(newArticle));

        // 2. 실제 데이터
        List<Article> articles = articleService.findAllArticles();

        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }
}
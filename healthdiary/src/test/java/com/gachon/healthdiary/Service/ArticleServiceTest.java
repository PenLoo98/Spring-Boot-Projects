package com.gachon.healthdiary.Service;

import com.gachon.healthdiary.DTO.ArticleForm;
import com.gachon.healthdiary.Entity.Article;
import com.gachon.healthdiary.Repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Transactional
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
//        expected.stream()
//                .forEach(newArticle -> articleRepository.save(newArticle));

        // 2. 실제 데이터
        List<Article> articles = articleService.findAllArticles();

        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void findArticle_있는_ID_검색() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "제목 1", "1111");

        // 2. 실제 데이터
        Article actual = articleService.findArticle(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void findArticle_없는_ID_검색() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;

        // 2. 실제 데이터
        Article actual = articleService.findArticle(id);

        // 3. 비교 및 검증
        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    void create_성공() {
        // 성공: id가 포함되지 않은 입력
        // 1. 예상 데이터
        String title = "제목 4";
        String content = "내용 4";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 2. 실제 데이터
        Article actual = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Transactional
    @Test
    void create_실패() {
        // 실패: id가 포함된 입력
        // 1. 예상 데이터
        Long id = 4L;
        String title = "제목 4";
        String content = "내용 4";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        // 2. 실제 데이터
        Article actual = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    void update_성공_존재하는_id와title와content_입력() {
        // 1. 예측 데이터
        Long id = 1L;
        String title = "제목 1 수정";
        String content = "content 1 수정";

        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = new Article(id,title,content);

        // 2. 실제 데이터
        Article actual = articleService.update(id,dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void update_성공_존재하는_id와title만_있는_입력() {
        // 1. 예측 데이터
        Long id = 1L;
        String title = "제목 1 수정";

        ArticleForm dto = new ArticleForm(id,title,null);
        Article expected = new Article(id,title,"1111");

        // 2. 실제 데이터
        Article actual = articleService.update(id,dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void update_실패_존재하지_않는_id입력(){
        // 1. 예측 데이터
        Long id = -1L;
        String title = "제목 4 수정";
        String content = "내용 4 수정";

        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;

        // 2. 실제 데이터
        Article actual = articleService.update(id,dto);

        // 3. 비교 및 검증
        assertEquals(expected, actual);
    }

    @Test
    void delete_성공_존재하는_ID_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = articleRepository.findById(id).orElse(null);

        // 2. 실제 데이터
        Article actual = articleService.delete(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void delete_실패_없는_ID_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;

        // 2. 실제 데이터
        Article actual = articleService.delete(id);

        // 3. 비교 및 검증
        assertEquals(expected, actual);
    }
}
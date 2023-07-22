package com.gachon.healthdiary.Controller;

import com.gachon.healthdiary.DTO.ArticleForm;
import com.gachon.healthdiary.Entity.Article;
import com.gachon.healthdiary.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("")
    public String index() {
        return "articles/index";
    }

    @GetMapping("/new")
    public String newArticle() {
        return "articles/new";
    }

    @PostMapping("/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());

        // DTO를 Entity로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());

        // Repository에게 Entity를 DB안에 저장하도록 요청
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());

        return "redirect:http://localhost:8080/articles";
    }

}

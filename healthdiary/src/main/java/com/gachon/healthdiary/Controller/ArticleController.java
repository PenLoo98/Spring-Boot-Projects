package com.gachon.healthdiary.Controller;

import com.gachon.healthdiary.DTO.ArticleForm;
import com.gachon.healthdiary.Entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping("")
    public String index() {
        return "articles/index";
    }

    @GetMapping("/new")
    public String newArticle() {
        return "articles/new";
    }

    @PostMapping("create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());

        // DTO를 Entity로 변환
        Article article = form.toEntity();


        // Repository에게 Entity를 DB안에 저장하도록 요청

        return "redirect:http://localhost:3000";
    }

}

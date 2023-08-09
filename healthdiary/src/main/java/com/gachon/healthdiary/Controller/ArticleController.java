package com.gachon.healthdiary.Controller;

import com.gachon.healthdiary.DTO.ArticleForm;
import com.gachon.healthdiary.DTO.CommentDTO;
import com.gachon.healthdiary.Entity.Article;
import com.gachon.healthdiary.Repository.ArticleRepository;
import com.gachon.healthdiary.Service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j // Simple Logging Facade for Java
@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    // articles 메인페이지
    @GetMapping("")
    public String index(Model model) {
        // 1. DB에서 데이터 가져오기
        List <Article> articleList = articleRepository.findAll();

        // 2. 모델에 가져온 데이터 등록
        model.addAttribute("articleList", articleList);

        // 3. 뷰페이지 설정
        return "articles/index";
    }

    // 글쓰기 페이지
    @GetMapping("/new")
    public String newArticle() {
        return "articles/new";
    }

    // 글쓰기 페이지의 제목,내용을 DB에 저장
    @PostMapping("/create")
    public String createArticle(ArticleForm form) {
//        System.out.println(form.toString());
        log.info(form.toString());

        // DTO를 Entity로 변환
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());

        // Repository에게 Entity를 DB안에 저장하도록 요청
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    // id로 articles의 데이터 조회
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        // URL로 전달된 변수를 확인
        log.info("id = " + id);

        // 1. id를 조회해 DB에서 해당 데이터 가져오기
//        Optional<Article> articleEntity = articleRepository.findById(id);
        Article foundArticle = articleRepository.findById(id).orElse(null);
        List<CommentDTO> commentsDTOs = commentService.getComments(id);

        // 2. 모델에 가져온 데이터 등록하기
        model.addAttribute("article", foundArticle);
        model.addAttribute("commentsDTOs", commentsDTOs);

        // 조회한 데이터를 사용자에게 보여주기 위한 뷰페이지 만들고 변환하기
        return "articles/show";
    }

    // id로 articles 수정
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article beforeArticle = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록
        model.addAttribute("beforeArticle", beforeArticle);
        // 뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/update")
    public String update(ArticleForm form){
        // 수정데이터 잘 받았는지 확인
        log.info(form.toString());

        // 1. DTO를 Entity로 저장
        Article editedArticle = form.toEntity();
        log.info(editedArticle.toString());

        // 2. Entity를 Repository를 통해 DB에 저장

        // 2-1 DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(editedArticle.getId()).orElse(null);

        // 2-2 기존 데이터 값을 갱신하기
        if(target != null){
            articleRepository.save(editedArticle);
        }

        // Update된 뷰페이지를 반환
        return "redirect:/articles/"+ editedArticle.getId();
    }

    // id로 articles 삭제
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        // delete 동작확인
        log.info("delete id = " + id);

        // 1. 삭제할 데이터 DB에서 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 대상 Entity가 존재하면 삭제
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("22", "id "+id+" delete success");
            log.info("delete id=" + id + " success");
        }

        // 3. 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }

}

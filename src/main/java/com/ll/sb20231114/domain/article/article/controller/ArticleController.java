package com.ll.sb20231114.domain.article.article.controller;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor // final 받은 녀석만 자동으로 생성자 만들어줌 그래서 생성자들 생략 가능
@Validated
public class ArticleController {
    //@Autowired // 필드 주입, final은 뺸다.
    private final ArticleService articleService;
    private final Rq rq; //rq는 대리자임, 근데 대리자가 넘 똑똑해서 요청 rq를 알고 토스해줌
    /*@Autowired // 만약 생성자가 하나라면 AUtoWired 생략 가능
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }*/


    @GetMapping("/article/detail/{id}")
    String showDetail(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();
        model.addAttribute("article", article);
        return "article/detail";
    }

    @GetMapping("/article/write")
    String showWrite() {
        return "article/write";
    }


    @Data
    public static class WriteForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @GetMapping("/article/list")
    String showList(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "article/list";
    }

    @PostMapping("/article/write")
    String Write(@Valid WriteForm writeForm) { // writeform 안에 Notblank 쓰게하기 위해선 Valid 쓴다

        Article article = articleService.write(writeForm.body, writeForm.title);
        String msg = "id %d is created".formatted(article.getId());
        return "redirect:/article/list?msg=" + msg;
    }

    @GetMapping("/article/delete/{id}")
    String delete(@PathVariable long id) {
        articleService.delete(id);
        String msg = "id %d is article deleted".formatted(id);
        return "redirect:/article/list?msg=" + msg;
    }

    @GetMapping("/article/modify/{id}")
    String ShowModify(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();
        model.addAttribute("article", article);
        return "article/modify";
    }

    @PostMapping("/article/getLastArticle")
    @ResponseBody
    Article getLastArticle() {
        return articleService.findLiastArticle();
    }

    @GetMapping("/article/getArticles")
    @ResponseBody
    List<Article> getArticles() {
        return articleService.findAll();
    }

    @GetMapping("/article/articleServicePointer")
    @ResponseBody
    String articleServicePointer() {
        return articleService.toString();
    }

    @GetMapping("/article/HttpServletRequestPointer")
    @ResponseBody
    String HttpServletRequestPointer(HttpServletRequest req) {
        return req.toString();
    }

    @GetMapping("/article/HttpServletResponsePointer")
    @ResponseBody
    String HttpServletResponsePointer(HttpServletResponse resp) {
        return resp.toString();
    }

    @GetMapping("/article/rqPointer")
    @ResponseBody
    String rqPointer() {
        return rq.toString();
    }

    @GetMapping("/article/rqTest")
    String showRqTest(Model model) {
        String rqToString = rq.toString();
        model.addAttribute("rqToString", rqToString);
        return "article/rqTest";
    }
}






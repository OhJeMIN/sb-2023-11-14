package com.ll.sb20231114.domain.article.article.controller;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
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

import java.util.List;

@Controller
@RequiredArgsConstructor // final 받은 녀석만 자동으로 생성자 만들어줌 그래서 생성자들 생략 가능
@Validated
public class ArticleController {
    //@Autowired // 필드 주입, final은 뺸다.
    private final ArticleService articleService;
    //private final Rq rq; //rq는 대리자임, 근데 대리자가 넘 똑똑해서 요청 rq를 알고 토스해줌
    /*@Autowired // 만약 생성자가 하나라면 AUtoWired 생략 가능
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }*/
    @GetMapping("/article/list")
    String showList(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "article/list";
    }

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



    @PostMapping("/article/write")
    String Write(@Valid WriteForm writeForm) { // writeform 안에 Notblank 쓰게하기 위해선 Valid 쓴다

        Article article = articleService.write(writeForm.body, writeForm.title);
        String msg = "%d번 게시물 생성되었습니다.".formatted(article.getId());
        return "redirect:/article/list?msg=" + msg;
    }

    @Data
    public static class ModifyForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @PostMapping("/article/modify/{id}")
    String modify(@PathVariable long id, @Valid ModifyForm modifyForm) { // writeform 안에 Notblank 쓰게하기 위해선 Valid 쓴다

        articleService.modify(id, modifyForm.body, modifyForm.title);
        String msg = "id %d is article modified".formatted(id);
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
}






package com.ll.sb20231114.domain.article.article.controller;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.global.rsData.RsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ArticleController {
    //@Autowired // 필드 주입, final은 뺸다.
    private final ArticleService  articleService;
    @Autowired // 만약 생성자가 하나라면 AUtoWired 생략 가능
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/article/write")
    String write(){
        return "article/write";
    }

    @PostMapping("/article/doWrite")
    @ResponseBody
    RsData doWrite(
            String title,
            String body
    ){
        Article article = articleService.write(body,title);
        RsData<Article> rs = new RsData<>(
                "S-1",
                "%d번 게시물이 작성되었습니다.".formatted(article.getId()),
                article
        );
        return rs;
    }
    @PostMapping("/article/getLastArticle")
    @ResponseBody
    Article getLastArticle(){
        return articleService.findLiastArticle();
    }

    @GetMapping("/article/getArticles")
    @ResponseBody
    List<Article> getArticles(){
        return articleService.findAll();
    }
}






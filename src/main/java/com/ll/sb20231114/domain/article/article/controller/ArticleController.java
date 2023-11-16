package com.ll.sb20231114.domain.article.article.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.global.rq.Rq;
import com.ll.sb20231114.global.rsData.RsData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor // final 받은 녀석만 자동으로 생성자 만들어줌 그래서 생성자들 생략 가능
public class ArticleController {
    //@Autowired // 필드 주입, final은 뺸다.
    private final ArticleService  articleService;
    private final Rq rq;
    /*@Autowired // 만약 생성자가 하나라면 AUtoWired 생략 가능
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }*/


    @GetMapping("/article/write")
    String showWrite(){
        return "article/write";
    }

    @PostMapping("/article/write")
    @ResponseBody
    RsData Write(
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

    @PostMapping("/article/write2")
    @SneakyThrows
    void Write2(
            HttpServletRequest req,
            HttpServletResponse resp

    ){
        String title = req.getParameter("title");
        String body = req.getParameter("body");
        Article article = articleService.write(title,body);
        RsData<Article> rs = new RsData<>(
                "S-1",
                "%d번 게시물이 작성되었습니다.".formatted(article.getId()),
                article
        );
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(objectMapper.writeValueAsString(rs));
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

    @GetMapping("/article/articleServicePointer")
    @ResponseBody
    String articleServicePointer(){
        return articleService.toString();
    }

    @GetMapping("/article/HttpServletRequestPointer")
    @ResponseBody
    String HttpServletRequestPointer(HttpServletRequest req){
        return req.toString();
    }

    @GetMapping("/article/HttpServletResponsePointer")
    @ResponseBody
    String HttpServletResponsePointer(HttpServletResponse resp){
        return resp.toString();
    }

    @GetMapping("/article/rqPointer")
    @ResponseBody
    String rqPointer(){
        return rq.toString();
    }
}






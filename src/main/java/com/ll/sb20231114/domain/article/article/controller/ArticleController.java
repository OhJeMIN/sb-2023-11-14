package com.ll.sb20231114.domain.article.article.controller;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.global.rq.Rq;
import com.ll.sb20231114.global.rsData.RsData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor // final 받은 녀석만 자동으로 생성자 만들어줌 그래서 생성자들 생략 가능
public class ArticleController {
    //@Autowired // 필드 주입, final은 뺸다.
    private final ArticleService  articleService;
    private final Rq rq; //rq는 대리자임, 근데 대리자가 넘 똑똑해서 요청 rq를 알고 토스해줌
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
        if( title == null || title.trim().length() == 0){
            return new RsData<>("F-1",
                    "제목을 입력해주세요");
        }
        if( body == null || body.trim().length() == 0){
            return new RsData<>("F-2",
                    "내용을 입력해주세요");
        }
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

    @GetMapping("/article/rqTest")
    String showRqTest(Model model){
        String rqToString = rq.toString();
        model.addAttribute("rqToString",rqToString);
        return "article/rqTest";
    }
}






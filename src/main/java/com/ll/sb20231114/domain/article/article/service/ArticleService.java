package com.ll.sb20231114.domain.article.article.service;

import com.ll.sb20231114.domain.article.article.Repository.ArticleRepository;
import com.ll.sb20231114.domain.article.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //저는 단 한번만 생성되고, 그 이우에는 재사용되는 객체입니다.
@RequiredArgsConstructor
public class ArticleService {
    /*@Autowired 필드 주입 --> 객체만들고 나중에 받는것
    private final ArticleRepository articleRepository;  final 불가능 왜냐면 만들면 이미 null 이 들어가 바꿀 수 없기 때문이다. */
    private  final ArticleRepository articleRepository;// 생성자로 주입할땐 final 가능 , 만들자마자 바로 예시로(칼) 이 들어가기 떄문에 된다.
    /*@Autowired // 생성자 주입 --> 객체만들면서 바로 주기, 만약 생성자가 하나라면 AUtoWired 생략 가능
    public ArticleService(ArticleRepository articleRepository){
        this.articleRepository =articleRepository;
    }*/

    public Article write(String title, String body) {
        Article article = new Article(title,body);
        articleRepository.save(article);
        return article;
    }

    public Article findLiastArticle() {
        return articleRepository.findLastArticle();
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}

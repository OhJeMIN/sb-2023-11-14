package com.ll.sb20231114.domain.article.article.service;

import com.ll.sb20231114.domain.article.article.Repository.ArticleRepository;
import com.ll.sb20231114.domain.article.article.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //저는 단 한번만 생성되고, 그 이우에는 재사용되는 객체입니다.
public class ArticleService {
    @Autowired
    private  ArticleRepository articleRepository;

    public Article write(String body, String title) {
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

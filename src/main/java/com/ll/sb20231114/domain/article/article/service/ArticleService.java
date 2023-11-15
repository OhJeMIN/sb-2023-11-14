package com.ll.sb20231114.domain.article.article.service;

import com.ll.sb20231114.domain.article.article.Repository.ArticleRepository;
import com.ll.sb20231114.domain.article.article.entity.Article;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepository();


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

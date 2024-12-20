package com.ism.services;

import com.ism.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleServiceImplInter {
    void create(Article article);
    Optional<Article> findById(int id);
    List<Article> findAll();
    void update(Article article);
    void delete(Article article);
    List<Article> getAvailableArticles();
}
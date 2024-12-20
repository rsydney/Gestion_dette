package com.ism.services;


import java.util.List;
import java.util.Optional;

import com.ism.entity.Article;
import com.ism.repository.ArticleRepository;

public class ArticleServiceImpl implements ArticleServiceImplInter {
    private  ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleServiceImpl() {
        //TODO Auto-generated constructor stub
    }

    @Override
    public void create(Article article) {
        articleRepository.add(article);
    }

    @Override
    public Optional<Article> findById(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.getAll();
    }

    @Override
    public void update(Article article) {
        articleRepository.update(article);
    }

    @Override
    public void delete(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public List<Article> getAvailableArticles() {
        return articleRepository.getAvailableArticles();
    }
}

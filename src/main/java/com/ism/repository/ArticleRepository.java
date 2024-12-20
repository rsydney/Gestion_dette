package com.ism.repository;

import com.ism.entity.Article;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArticleRepository implements ArticleRepositoryInter {
    private List<Article> articles;

    public ArticleRepository(List<Article> articles) {
        this.articles = articles;
    }

    public ArticleRepository() {
        //TODO Auto-generated constructor stub
    }

    @Override
    public void add(Article article) {
        articles.add(article);
    }

    @Override
    public List<Article> getAll() {
        return articles;
    }

    @Override
    public Optional<Article> findById(int id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst();
    }

    @Override
    public List<Article> getAvailableArticles() {
        return articles.stream()
                .filter(article -> article.getQuantity() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Article findByName(String name) {
        return articles.stream()
                .filter(article -> article.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void delete(Article article) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public void update(Article article) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<Article> findBy(String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }
}

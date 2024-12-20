package com.ism.repository;

import com.ism.entity.Article;
import com.ism.repository.core.Repository;

import java.util.List;

public interface ArticleRepositoryInter extends Repository<Article> {
    List<Article> getAvailableArticles();
    Article findByName(String name);
}

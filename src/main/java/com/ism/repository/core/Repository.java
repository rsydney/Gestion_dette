package com.ism.repository.core;

import java.util.List;
import java.util.Optional;



public interface Repository<T> {
    void add(T entity);
    List<T> getAll();
    Optional<T> findById(int id);
    Optional<T> findBy(String data);
}

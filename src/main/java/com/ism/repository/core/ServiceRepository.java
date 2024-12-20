package com.ism.repository.core;


import java.util.List;
import java.util.Optional;

public interface ServiceRepository<T, ID> {
    void create(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void update(T entity); 
    void delete(T entity);
    
}

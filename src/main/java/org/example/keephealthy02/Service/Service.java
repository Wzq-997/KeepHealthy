package org.example.keephealthy02.Service;


public interface Service<T> {
    T selectById(String id);
    int insert(T entity);
    int update(T entity);
    int deleteById(Long id);
}

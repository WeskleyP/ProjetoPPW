package com.crm.service;

import java.util.List;

public interface GenericService<T, T1> {
    List<T> findAll();
    T save(T entity);
    T update(T entity);
    T getOne(T1 id);
    T findById(T1 id);
    void deleteById(T1 id);
}

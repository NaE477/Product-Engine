package com.portfolio.naeim.service.interfaces;

import java.util.List;

public interface BaseService<T, ID> {
    T getById(ID id);
    List<T> getAll();
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}
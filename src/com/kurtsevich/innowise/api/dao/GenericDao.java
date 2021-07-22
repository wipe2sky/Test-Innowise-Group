package com.kurtsevich.innowise.api.dao;

import com.kurtsevich.innowise.model.BaseEntity;

import java.util.List;

public interface GenericDao<T extends BaseEntity> {

    void save(T entity);

    T getById(Integer id);

    List<T> getAll();

    void deleteById(Integer id);

    void update(T entity);
}

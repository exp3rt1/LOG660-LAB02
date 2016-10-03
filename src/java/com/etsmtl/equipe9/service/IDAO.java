package com.etsmtl.equipe9.service;

public interface IDAO<T> {
    public T findAll();
    public T findById(Long id);
    public boolean insert(T obj);
    public boolean update(T obj);
    public boolean delete(T obj);
}

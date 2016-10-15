package com.etsmtl.equipe9.service;

import java.util.List;

public interface IDAO<T, PK> {
    public List<T> findAll();
    public T findById(PK id);
    public List<T> findById(List<PK> listeId);
    public boolean insert(T obj);
    public boolean update(T obj);
    public boolean delete(T obj);
}

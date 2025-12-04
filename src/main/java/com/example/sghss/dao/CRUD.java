package com.example.sghss.dao;
import java.util.List;

public interface CRUD<T, ID> {
    T pesquisarPeloId(ID id);
    List<T> lista();
    void create(T entity);
    void update(T entity);
    void delete(ID id);
    

}

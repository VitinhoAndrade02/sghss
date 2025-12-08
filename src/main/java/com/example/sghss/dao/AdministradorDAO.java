package com.example.sghss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.sghss.model.Administrador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Repository
public class AdministradorDAO implements CRUD<Administrador, Long> {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Administrador pesquisarPeloId(Long id) {
        return entityManager.find(Administrador.class, id);
    }

    @Override
    public void create(Administrador entity) {
        entityManager.persist(entity);
    }

     @Override
    public List<Administrador> lista() { 
        TypedQuery<Administrador> query = entityManager.createQuery("SELECT a FROM Administrador a", Administrador.class);
        return query.getResultList();
    }

    @Override
    public void update(Administrador entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Administrador administrador = entityManager.find(Administrador.class, id);
        if (administrador != null) {
            entityManager.remove(administrador);
        }
    }



    
    
}

package com.example.sghss.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.sghss.model.Profissional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProfissionalDAO implements CRUD<Profissional, Long> {

@PersistenceContext
private EntityManager entityManager;

    @Override
    public Profissional pesquisarPeloId(Long id) {
        return entityManager.find(Profissional.class, id);
    }

    @Override
    public List<Profissional> lista() {
        TypedQuery<Profissional> query = entityManager.createQuery("SELECT p FROM Profissional p", Profissional.class);
        return query.getResultList();
    }

    
    @Override
    public void create(Profissional profissional) {
        entityManager.persist(profissional);
    }

    
    @Override
    public void update(Profissional profissional) {
        entityManager.merge(profissional);
    }

    
    @Override
    public void delete(Long id) {
        Profissional profissional = pesquisarPeloId(id);
        if (profissional != null) {
            entityManager.remove(profissional);
        }
    }
}

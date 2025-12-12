package com.example.sghss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.sghss.model.Unidade;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UnidadeDAO implements CRUD<Unidade, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Unidade pesquisarPeloId(Long id) {
        return entityManager.find(Unidade.class, id);
    }

    @Override
    public List<Unidade> lista() {
        TypedQuery<Unidade> query = entityManager.createQuery("SELECT u FROM Unidade u", Unidade.class);
        return query.getResultList();
    }

    @Override
    public void create(Unidade unidade) {
        entityManager.persist(unidade);
    }

    @Override
    public void update(Unidade unidade) {
        entityManager.merge(unidade);
    }

    @Override
    public void delete(Long id) {
        Unidade unidade = entityManager.find(Unidade.class, id);
        if (unidade != null) {
            entityManager.remove(unidade);
        }
    }
}

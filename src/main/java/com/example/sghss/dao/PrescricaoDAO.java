package com.example.sghss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.sghss.model.Prescricao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PrescricaoDAO implements CRUD<Prescricao, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Prescricao pesquisarPeloId(Long id) {
        return entityManager.find(Prescricao.class, id);
    }

    @Override
    public List<Prescricao> lista() {
        TypedQuery<Prescricao> query = entityManager.createQuery(
            "SELECT p FROM Prescricao p", Prescricao.class);
        return query.getResultList();
    }

    @Override
    public void create(Prescricao prescricao) {
        entityManager.persist(prescricao);
    }

    @Override
    public void update(Prescricao prescricao) {
        entityManager.merge(prescricao);
    }

    @Override
    public void delete(Long id) {
        Prescricao prescricao = entityManager.find(Prescricao.class, id);
        if (prescricao != null) {
            entityManager.remove(prescricao);
        }
    }
}

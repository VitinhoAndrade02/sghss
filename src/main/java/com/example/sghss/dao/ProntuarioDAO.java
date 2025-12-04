package com.example.sghss.dao;
import org.springframework.stereotype.Repository;

import com.example.sghss.model.Prontuario;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public class ProntuarioDAO implements CRUD<Prontuario, Long> {

@PersistenceContext
private EntityManager entityManager;

    @Override
    public Prontuario pesquisarPeloId(Long id) {
        return entityManager.find(Prontuario.class, id);
    }

    @Override
    public List<Prontuario> lista() {
        TypedQuery<Prontuario> query = entityManager.createQuery("SELECT pr FROM Prontuario pr", Prontuario.class);
        return (List<Prontuario>) query.getResultList();
    }

    @Transactional
    @Override
    public void create(Prontuario prontuario) {
        entityManager.persist(prontuario);
    }

    @Transactional
    @Override
    public void update(Prontuario prontuario) {
        entityManager.merge(prontuario);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Prontuario prontuario = pesquisarPeloId(id);
        if (prontuario != null) {
            entityManager.remove(prontuario);
        }
    }

}

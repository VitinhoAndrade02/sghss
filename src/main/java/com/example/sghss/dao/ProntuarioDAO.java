package com.example.sghss.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.sghss.model.Prontuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProntuarioDAO implements CRUD<Prontuario, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Prontuario pesquisarPeloId(Long id) {
        return entityManager.find(Prontuario.class, id);
    }

    @Override
    public List<Prontuario> lista() {
        TypedQuery<Prontuario> query = entityManager.createQuery(
        "SELECT pr FROM Prontuario pr LEFT JOIN FETCH pr.profissional", Prontuario.class);
        return query.getResultList();
    }

    @Override
    public void create(Prontuario prontuario) {
        entityManager.persist(prontuario);
    }

    @Override
    public void update(Prontuario prontuario) {
        entityManager.merge(prontuario);
    }

    @Override
    public void delete(Long id) {
        Prontuario prontuario = entityManager.find(Prontuario.class, id);
        if (prontuario != null) {
            entityManager.remove(prontuario);
        }
    }
    
    public List<Prontuario> findByProfissionalId(Long profissionalId) {
    TypedQuery<Prontuario> query = entityManager.createQuery(
        "SELECT pr FROM Prontuario pr WHERE pr.profissional.id = :profissionalId", Prontuario.class);
    query.setParameter("profissionalId", profissionalId);
    return query.getResultList();
    }

}

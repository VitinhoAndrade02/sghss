package com.example.sghss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.sghss.model.Teleconsulta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class TeleconsultaDAO implements CRUD<Teleconsulta, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Teleconsulta pesquisarPeloId(Long id) {
        return entityManager.find(Teleconsulta.class, id);
    }

    @Override
    public List<Teleconsulta> lista() {
        TypedQuery<Teleconsulta> query = entityManager.createQuery(
            "SELECT t FROM Teleconsulta t", Teleconsulta.class);
        return query.getResultList();
    }

    @Override
    public void create(Teleconsulta teleconsulta) {
        entityManager.persist(teleconsulta);
    }

    @Override
    public void update(Teleconsulta teleconsulta) {
        entityManager.merge(teleconsulta);
    }

    @Override
    public void delete(Long id) {
        Teleconsulta teleconsulta = entityManager.find(Teleconsulta.class, id);
        if (teleconsulta != null) {
            entityManager.remove(teleconsulta);
        }
    }
}

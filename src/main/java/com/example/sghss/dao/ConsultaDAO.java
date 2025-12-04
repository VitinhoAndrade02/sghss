package com.example.sghss.dao;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.example.sghss.model.Consulta;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ConsultaDAO implements CRUD<Consulta, Long> {

@PersistenceContext
private EntityManager entityManager;

    @Override
    public Consulta pesquisarPeloId(Long id) {
        return entityManager.find(Consulta.class, id);
    }

    @Override
    public List<Consulta> lista() {
        TypedQuery<Consulta> query = entityManager.createQuery("SELECT c FROM Consulta c", Consulta.class);
        return (List<Consulta>) query.getResultList();
    }

    @Transactional
    @Override
    public void create(Consulta consulta) {
        entityManager.persist(consulta);
    }

    @Transactional
    @Override
    public void update(Consulta consulta) {
        entityManager.merge(consulta);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Consulta consulta = pesquisarPeloId(id);
        if (consulta != null) {
            entityManager.remove(consulta);
        }
    }
}

package com.example.sghss.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.sghss.model.Leito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class LeitoDAO implements CRUD<Leito, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Leito pesquisarPeloId(Long id) {
        return entityManager.find(Leito.class, id);
    }

    @Override
    public List<Leito> lista() {
        TypedQuery<Leito> query = entityManager.createQuery("SELECT l FROM Leito l", Leito.class);
        return query.getResultList();
    }

    @Override
    public void create(Leito leito) {
        entityManager.persist(leito);
    }

    @Override
    public void update(Leito leito) {
        entityManager.merge(leito);
    }

    @Override
    public void delete(Long id) {
        Leito leito = entityManager.find(Leito.class, id);
        if (leito != null) {
            entityManager.remove(leito);
        }
    }
}

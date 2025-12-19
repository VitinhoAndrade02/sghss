package com.example.sghss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.sghss.model.RelFinanceiro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class RelFinanceiroDAO implements CRUD<RelFinanceiro, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RelFinanceiro pesquisarPeloId(Long id) {
        return entityManager.find(RelFinanceiro.class, id);
    }

    @Override
    public List<RelFinanceiro> lista() {
        TypedQuery<RelFinanceiro> query = entityManager.createQuery(
            "SELECT r FROM RelFinanceiro r", RelFinanceiro.class);
        return query.getResultList();
    }

    @Override
    public void create(RelFinanceiro relatorio) {
        entityManager.persist(relatorio);
    }

    @Override
    public void update(RelFinanceiro relatorio) {
        entityManager.merge(relatorio);
    }

    @Override
    public void delete(Long id) {
       entityManager.remove(id);
    }
}

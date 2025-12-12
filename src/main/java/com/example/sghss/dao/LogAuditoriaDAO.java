package com.example.sghss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.sghss.model.LogAuditoria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class LogAuditoriaDAO implements CRUD<LogAuditoria, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LogAuditoria pesquisarPeloId(Long id) {
        return entityManager.find(LogAuditoria.class, id);
    }

    @Override
    public List<LogAuditoria> lista() {
        TypedQuery<LogAuditoria> query = entityManager.createQuery(
            "SELECT l FROM LogAuditoria l", LogAuditoria.class);
        return query.getResultList();
    }

    @Override
    public void create(LogAuditoria log) {
        entityManager.persist(log);
    }

    @Override
    public void update(LogAuditoria log) {
        entityManager.merge(log);
    }

    @Override
    public void delete(Long id) {
        LogAuditoria log = entityManager.find(LogAuditoria.class, id);
        if (log != null) {
            entityManager.remove(log);
        }
    }
}

package com.example.sghss.dao;

import com.example.sghss.model.LogAuditoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public class LogAuditoriaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(LogAuditoria log) {
        entityManager.persist(log);
    }

    public List<LogAuditoria> lista() {
        TypedQuery<LogAuditoria> query = entityManager.createQuery(
            "SELECT l FROM LogAuditoria l ORDER BY l.dataHora DESC", LogAuditoria.class);
        return query.getResultList();
    }
}
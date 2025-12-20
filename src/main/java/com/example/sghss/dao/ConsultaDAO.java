package com.example.sghss.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.sghss.model.Consulta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class ConsultaDAO {

    @PersistenceContext
    private EntityManager em;

    public void create(Consulta c) { em.persist(c); }
    
    public List<Consulta> lista() {
        return em.createQuery("SELECT c FROM Consulta c ORDER BY c.dataHora DESC", Consulta.class)
                 .getResultList();
    }

    public boolean existsPorMedicoEHorario(Long profissionalId, LocalDateTime data) {
        String jpql = "SELECT COUNT(c) FROM Consulta c WHERE c.profissional.id = :pId AND c.dataHora = :data";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("pId", profissionalId)
                .setParameter("data", data)
                .getSingleResult();
        return count > 0;
    }

    public void delete(Long id) {
        Consulta c = em.find(Consulta.class, id);
        if (c != null) em.remove(c);
    }
}
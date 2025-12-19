package com.example.sghss.dao;

import java.time.LocalDateTime;
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
    
    public void salvar(Teleconsulta teleconsulta) {
        try {
            // O persist transforma o objeto em uma linha na tabela
            entityManager.persist(teleconsulta);
        } catch (Exception e) {
            // Aqui você pode tratar erros de banco (ex: violação de constraint)
            throw new RuntimeException("Erro ao salvar a teleconsulta", e);
        }
    }

    // Método para verificar se o médico já tem consulta nesse horário
    public boolean existsPorMedicoEHorario(Long profissionalId, LocalDateTime data) {
        String jpql = "SELECT COUNT(t) FROM Teleconsulta t " +
                      "WHERE t.profissional.id = :idProf " +
                      "AND t.data = :dataConsulta";
        
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("idProf", profissionalId)
                .setParameter("dataConsulta", data)
                .getSingleResult();
        
        return count > 0;
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

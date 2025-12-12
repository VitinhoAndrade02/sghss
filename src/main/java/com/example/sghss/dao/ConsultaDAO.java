package com.example.sghss.dao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.sghss.model.Consulta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
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
        return query.getResultList();
    }

    @Override
    public void create(Consulta consulta) {
        entityManager.persist(consulta);
    }

    @Override
    public void update(Consulta consulta) {
        entityManager.merge(consulta);
    }

    @Override
    public void delete(Long id) {
        Consulta consulta = pesquisarPeloId(id);
        if (consulta != null) {
            entityManager.remove(consulta);
        }
    }
    
    public List<LocalTime> horariosOcupados(LocalDate data) {
        TypedQuery<LocalTime> query = entityManager.createQuery(
            "SELECT c.horaConsulta FROM Consulta c WHERE c.dataConsulta = :data",
            LocalTime.class);

        query.setParameter("data", data);
        return query.getResultList();
    }
}

package com.example.sghss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.sghss.model.Paciente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository 
@Transactional
public class PacienteDAO implements CRUD<Paciente, Long> {

    @PersistenceContext 
    private EntityManager entityManager;

    @Override
    public Paciente pesquisarPeloId(Long id) {
        return entityManager.find(Paciente.class, id); 
    }

    @Override
    public void create(Paciente entity) {
        entityManager.persist(entity);
    }
    
     @Override
    public List<Paciente> lista() { 
        TypedQuery<Paciente> query = entityManager.createQuery("SELECT p FROM Paciente p", Paciente.class);
        return query.getResultList();
    }

    @Override
    public void update(Paciente entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) { 
        Paciente paciente = entityManager.find(Paciente.class, id);
        if (paciente != null) {
            entityManager.remove(paciente);
        }
    }
}
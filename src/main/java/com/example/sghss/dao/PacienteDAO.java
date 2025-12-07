package com.example.sghss.dao;

import com.example.sghss.model.Paciente;
import org.springframework.stereotype.Repository; 
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager; 
import jakarta.persistence.PersistenceContext; 
import jakarta.persistence.TypedQuery; // Necessário para a consulta lista()
import java.util.List;

@Repository 
public class PacienteDAO {

    @PersistenceContext 
    private EntityManager entityManager;

    public Paciente pesquisarPeloId(Long id) {
        return entityManager.find(Paciente.class, id); 
    }

    @Transactional
    public void create(Paciente paciente) {
        entityManager.persist(paciente);
    }
    
    @Transactional
    public Paciente update(Paciente paciente) {
        // Usa merge para atualizar a entidade
        return entityManager.merge(paciente);
    }

    @Transactional
    public List<Paciente> lista() { 
        // Cria a consulta JPQL para buscar todos os pacientes
        TypedQuery<Paciente> query = entityManager.createQuery("SELECT p FROM Paciente p", Paciente.class);
        return query.getResultList();
    }

    @Transactional
    public void delete(Long id) { 
        Paciente paciente = entityManager.find(Paciente.class, id);
        if (paciente != null) {
            entityManager.remove(paciente);
        }
    }
}
package com.example.springboot.dao;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.example.springboot.model.Paciente;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;


@Repository   
public class PacienteDAO implements CRUD<Paciente, Long> {

@PersistenceContext
private EntityManager entityManager;

    @Override
    public Paciente pesquisarPeloId(Long id) {
        return entityManager.find(Paciente.class, id);
    }

    @Override
    public List<Paciente> lista() {
        Query query = entityManager.createQuery("SELECT p FROM Paciente p");
        return (List<Paciente>) query.getResultList();
    }

    @Override
    public void create(Paciente paciente) {
        entityManager.persist(paciente);
    }

    @Override
    public void update(Paciente paciente) {
        entityManager.merge(paciente);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Paciente paciente = pesquisarPeloId(id);
        if (paciente != null) {
            entityManager.remove(paciente);
        }
    }

}

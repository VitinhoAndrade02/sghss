package com.example.sghss.dao;
import org.springframework.stereotype.Repository;
import com.example.sghss.model.Usuario;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;  
import jakarta.transaction.Transactional;
import java.util.List;


@Repository
public class UsuarioDAO implements CRUD<Usuario, Long> {

@PersistenceContext
private EntityManager entityManager;

    @Override
    public Usuario pesquisarPeloId(Long id) {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> lista() {
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return (List<Usuario>) query.getResultList();
    }

    @Transactional
    @Override
    public void create(Usuario usuario) {
        entityManager.persist(usuario);
    }
    
    @Transactional
    @Override   
    public void update(Usuario usuario) {
        entityManager.merge(usuario);

    }
    @Transactional
    @Override
    public void delete(Long Id) {
        Usuario usuario = entityManager.find(Usuario.class, Id);
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }

}

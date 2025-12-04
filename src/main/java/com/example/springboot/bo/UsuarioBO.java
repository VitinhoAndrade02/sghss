package com.example.springboot.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.dao.UsuarioDAO;
import com.example.springboot.model.Usuario;
import com.example.springboot.dao.CRUD;
import java.util.List;

@Service
public class UsuarioBO implements CRUD<Usuario, Long> {
   
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public Usuario pesquisarPeloId(Long id) {
        return usuarioDAO.pesquisarPeloId(id);
    }
    @Override
    public List<Usuario> lista() {
        return usuarioDAO.lista();
    }       
    @Override
    public void create(Usuario usuario) {
        usuarioDAO.create(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        usuarioDAO.update(usuario);
    }

    @Override
    public void delete(Long id) {
        usuarioDAO.delete(id);
    }

}

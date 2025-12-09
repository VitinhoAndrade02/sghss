package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.CRUD;
import com.example.sghss.dao.UsuarioDAO;
import com.example.sghss.model.Usuario;

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

    public void inativa(Usuario usuario) {
        usuario.setAtivo(false);
        usuarioDAO.update(usuario);
    }

    public void ativa(Usuario usuario) {
        usuario.setAtivo(true);
        usuarioDAO.update(usuario);
    }

}

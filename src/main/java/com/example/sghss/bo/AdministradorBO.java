package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.AdministradorDAO;
import com.example.sghss.dao.CRUD;
import com.example.sghss.model.Administrador;

@Service
public class AdministradorBO implements CRUD<Administrador, Long> {
   
    @Autowired
    private AdministradorDAO dao;

    @Override
    public Administrador pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }

    @Override
    public void create(Administrador administrador) {
        dao.create(administrador);
    }

    @Override
    public List<Administrador> lista() {
        return dao.lista();
    }

    @Override
    public void update(Administrador administrador) {
        dao.update(administrador);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }   

    public void inativa(Administrador administrador) {
        administrador.setAtivo(false);
        dao.update(administrador);
    }
    public void ativa(Administrador administrador) {
        administrador.setAtivo(true);
        dao.update(administrador);
    }
}

package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.CRUD;
import com.example.sghss.dao.PrescricaoDAO;
import com.example.sghss.model.Prescricao;

@Service
public class PrescricaoBO implements CRUD<Prescricao, Long>{

    @Autowired
    private PrescricaoDAO dao;

    @Override
    public Prescricao pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }

    @Override
    public List<Prescricao> lista() {
        return dao.lista();
    }

    @Override
    public void create(Prescricao prescricao) {
        dao.create(prescricao);
    }

    @Override
    public void update(Prescricao prescricao) {
        dao.update(prescricao);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
    
    public List<Prescricao> listaPorProfissional(Long profissionalId) {
    return dao.findByProfissionalId(profissionalId); 
    }
}

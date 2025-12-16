package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.CRUD;
import com.example.sghss.dao.LeitoDAO;
import com.example.sghss.model.Leito;

@Service
public class LeitoBO implements CRUD<Leito, Long> {

    @Autowired
    private LeitoDAO dao;

    @Override
    public Leito pesquisarPeloId(Long id) { 
        return dao.pesquisarPeloId(id); 
    }

    @Override
    public List<Leito> lista() { 
        return dao.lista(); 
    }

    @Override
    public void create(Leito leito) { 
        dao.create(leito); 
    }

    @Override
    public void update(Leito leito) { 
        dao.update(leito); 
    }

    @Override
    public void delete(Long id) { 
        dao.delete(id); 
    }

    public List<Leito> listaPorUnidade(Long unidadeId) {
    return dao.findByUnidadeId(unidadeId); 
    }

}   

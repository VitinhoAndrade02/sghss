package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.UnidadeDAO;
import com.example.sghss.model.Unidade;

@Service
public class UnidadeBO {

    @Autowired
    private UnidadeDAO dao;

    public Unidade pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }

    public List<Unidade> lista() {
        return dao.lista();
    }

    public void create(Unidade unidade) {
        dao.create(unidade);
    }

    public void update(Unidade unidade) {
        dao.update(unidade);
    }

    public void delete(Long id) {
        dao.delete(id);
    }
}

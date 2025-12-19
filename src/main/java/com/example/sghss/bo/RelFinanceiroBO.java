package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.CRUD;
import com.example.sghss.dao.RelFinanceiroDAO;
import com.example.sghss.model.RelFinanceiro;


@Service
public class RelFinanceiroBO implements CRUD<RelFinanceiro, Long> {

    @Autowired
    private RelFinanceiroDAO dao;

    @Override
    public RelFinanceiro pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }

    @Override
    public List<RelFinanceiro> lista() {
        return dao.lista();
    }

    @Override
    public void create(RelFinanceiro relatorio) {
        dao.create(relatorio);
    }

    @Override
    public void update(RelFinanceiro relatorio) {
        dao.update(relatorio);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}

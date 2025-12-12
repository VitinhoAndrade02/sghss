package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.RelFinanceiroDAO;
import com.example.sghss.model.RelFinanceiro;

@Service
public class RelFinanceiroBO {

    @Autowired
    private RelFinanceiroDAO dao;

    public RelFinanceiro pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }

    public List<RelFinanceiro> lista() {
        return dao.lista();
    }

    public void create(RelFinanceiro relatorio) {
        dao.create(relatorio);
    }

    public void update(RelFinanceiro relatorio) {
        dao.update(relatorio);
    }

    public void delete(Long id) {
        dao.delete(id);
    }
}

package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.PrescricaoDAO;
import com.example.sghss.model.Prescricao;

@Service
public class PrescricaoBO {

    @Autowired
    private PrescricaoDAO dao;

    public Prescricao pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }

    public List<Prescricao> lista() {
        return dao.lista();
    }

    public void create(Prescricao prescricao) {
        dao.create(prescricao);
    }

    public void update(Prescricao prescricao) {
        dao.update(prescricao);
    }

    public void delete(Long id) {
        dao.delete(id);
    }
}

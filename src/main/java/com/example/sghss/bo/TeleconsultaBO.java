package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.TeleconsultaDAO;
import com.example.sghss.model.Teleconsulta;

@Service
public class TeleconsultaBO {

    @Autowired
    private TeleconsultaDAO dao;

    public Teleconsulta pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }

    public List<Teleconsulta> lista() {
        return dao.lista();
    }

    public void create(Teleconsulta teleconsulta) {
        dao.create(teleconsulta);
    }

    public void update(Teleconsulta teleconsulta) {
        dao.update(teleconsulta);
    }

    public void delete(Long id) {
        dao.delete(id);
    }
}

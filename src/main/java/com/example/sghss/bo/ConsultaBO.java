package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.ConsultaDAO;
import com.example.sghss.model.Consulta;

import jakarta.transaction.Transactional;

@Service
public class ConsultaBO {

    @Autowired
    private ConsultaDAO dao;

    @Transactional
    public void create(Consulta consulta) {

        if (consulta.getConsultorio() == null || consulta.getConsultorio().isEmpty()) {
            String padrao = consulta.getProfissional().getConsultorioPadrao();
            consulta.setConsultorio(padrao != null ? padrao : "Sala de Triagem");
        }

        dao.create(consulta);
    }

    public List<Consulta> lista() { 
        return dao.lista(); 
    }
    public void delete(Long id) { 
        dao.delete(id); 
    }
}
package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.CRUD;
import com.example.sghss.dao.ConsultaDAO;
import com.example.sghss.model.Consulta;
import com.example.sghss.model.TipoConsulta;

@Service
public class ConsultaBO implements CRUD<Consulta, Long> {
    
    @Autowired  
    private ConsultaDAO dao;

    @Override
    public Consulta pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }        

    @Override
    public List<Consulta> lista() {
        return dao.lista();
    }

    @Override
    public void create(Consulta consulta) {
        validarConsulta(consulta);
        dao.create(consulta);
    }

    @Override
    public void update(Consulta consulta) {
        validarConsulta(consulta);
        dao.update(consulta);
    }
    
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    /* ================= VALIDAÇÕES ================= */
    private void validarConsulta(Consulta consulta) {

        if (consulta.getPaciente() == null) {
            throw new IllegalArgumentException("Paciente é obrigatório");
        }

        if (consulta.getProfissional() == null) {
            throw new IllegalArgumentException("Profissional é obrigatório");
        }

        if (consulta.getDataConsulta() == null) {
            throw new IllegalArgumentException("Data da consulta é obrigatória");
        }

        if (consulta.getHoraConsulta() == null) {
            throw new IllegalArgumentException("Hora da consulta é obrigatória");
        }

        if (consulta.getTipoConsulta() == null) {
            throw new IllegalArgumentException("Tipo da consulta é obrigatório");
        }

        // Regra da teleconsulta
        if (consulta.getTipoConsulta() == TipoConsulta.TELECONSULTA) {
            if (consulta.getLinkTeleconsulta() == null || consulta.getLinkTeleconsulta().isBlank()) {
                throw new IllegalArgumentException("Link da teleconsulta é obrigatório");
            }
        } else {
            // presencial não pode ter link
            consulta.setLinkTeleconsulta(null);
        }
    }
}

package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sghss.dao.PacienteDAO;
import com.example.sghss.model.Paciente; 

@Service
public class PacienteBO {

    @Autowired
    private PacienteDAO dao; 

    @Transactional
    public void create(Paciente paciente) {
        dao.create(paciente);
    }
    
    public Paciente pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }

    public List<Paciente> lista() {
        return dao.lista();
    }
    
    @Transactional
    public void update(Paciente paciente) {
        dao.update(paciente);
    }
    
    @Transactional
    public void delete(Long id) {
        
        dao.delete(id);
    }
}
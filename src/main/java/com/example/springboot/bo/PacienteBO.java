package com.example.springboot.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.dao.PacienteDAO;
import com.example.springboot.model.Paciente;
import com.example.springboot.dao.CRUD;
import java.util.List;


@Service
public class PacienteBO implements CRUD<Paciente, Long> {
    @Autowired
    private PacienteDAO dao;

    @Override
    public Paciente pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }        

    @Override
    public List<Paciente> lista() {
        return dao.lista();
    }

    @Override
    public void create(Paciente paciente) {
        dao.create(paciente);
    }

    @Override
    public void update(Paciente paciente) {
        dao.update(paciente);
    }
    
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
    

}

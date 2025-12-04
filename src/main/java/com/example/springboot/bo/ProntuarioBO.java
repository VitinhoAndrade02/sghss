package com.example.springboot.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.dao.ProntuarioDAO;
import com.example.springboot.model.Prontuario;
import com.example.springboot.dao.CRUD;
import java.util.List;

@Service    
public class ProntuarioBO implements CRUD<Prontuario, Long> {

    @Autowired
    private ProntuarioDAO prontuarioDAO;
    @Override
    public Prontuario pesquisarPeloId(Long id) {
        return prontuarioDAO.pesquisarPeloId(id);
    }   

    @Override
    public List<Prontuario> lista() {
        return prontuarioDAO.lista();
    }

    @Override
    public void create(Prontuario prontuario) {
        prontuarioDAO.create(prontuario);
    }

    @Override
    public void update(Prontuario prontuario) {
        prontuarioDAO.update(prontuario);
    }

    @Override
    public void delete(Long id) {
        prontuarioDAO.delete(id);
    }

}
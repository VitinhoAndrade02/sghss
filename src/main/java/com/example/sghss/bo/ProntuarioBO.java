package com.example.sghss.bo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sghss.dao.CRUD;
import com.example.sghss.dao.ProntuarioDAO;
import com.example.sghss.model.Prontuario;

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

    public List<Prontuario> listaPorProfissional(Long profissionalId) {
    return prontuarioDAO.findByProfissionalId(profissionalId); 
    }

}
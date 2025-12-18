package com.example.sghss.bo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.CRUD;
import com.example.sghss.dao.ProfissionalDAO;
import com.example.sghss.model.Especialidade;
import com.example.sghss.model.Profissional;



@Service
public class ProfissionalBO implements CRUD<Profissional, Long> {

    @Autowired
    private ProfissionalDAO dao;

    @Override
    public Profissional pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }        

    @Override
    public List<Profissional> lista() {
        return dao.lista();
    }

    @Override
    public void create(Profissional profissional) {
        dao.create(profissional);
    }

    @Override
    public void update(Profissional profissional) {
        dao.update(profissional);
    }
    
    @Override
    public void delete(Long id) {
        Profissional profissional = pesquisarPeloId(id);

        if (profissional.getPrescricoes() != null &&
            !profissional.getPrescricoes().isEmpty()) {
            System.out.println("Profissional possui prescrições, apagando em cascata");
        }

        dao.delete(id);
    }

    public List<Profissional> buscarPorEspecialidade(Especialidade especialidade) {
    return dao.buscarPorEspecialidade(especialidade);
    }   

    
}

package com.example.sghss.bo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.CRUD;
import com.example.sghss.dao.ConsultaDAO;
import com.example.sghss.model.Consulta;


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
        dao.create(consulta);
    }

    @Override
    public void update(Consulta consulta) {
        dao.update(consulta);
    }
    
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
    
}

package com.example.springboot.bo;
import org.springframework.stereotype.Service;
import com.example.springboot.dao.ConsultaDAO;
import com.example.springboot.model.Consulta;
import com.example.springboot.dao.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


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

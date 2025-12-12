package com.example.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sghss.dao.LogAuditoriaDAO;
import com.example.sghss.model.LogAuditoria;

@Service
public class LogAuditoriaBO {

    @Autowired
    private LogAuditoriaDAO dao;

    public LogAuditoria pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }

    public List<LogAuditoria> lista() {
        return dao.lista();
    }

    public void create(LogAuditoria log) {
        dao.create(log);
    }

    public void update(LogAuditoria log) {
        dao.update(log);
    }

    public void delete(Long id) {
        dao.delete(id);
    }
}

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

    public void create(LogAuditoria log) {
        dao.create(log);
    }

    public List<LogAuditoria> lista() {
        return dao.lista();
    }
}
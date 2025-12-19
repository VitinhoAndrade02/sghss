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

    // Utilizado pelo Listener para gravar o log automaticamente
    public void create(LogAuditoria log) {
        dao.create(log);
    }

    // Utilizado pelo Controller para exibir o histórico na tela
    public List<LogAuditoria> lista() {
        return dao.lista();
    }
}
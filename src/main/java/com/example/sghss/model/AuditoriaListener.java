package com.example.sghss.model;

import com.example.sghss.bo.LogAuditoriaBO;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditoriaListener {

    private static LogAuditoriaBO logBO;

    // O Spring chama este método logo que o sistema inicia para "entregar" o BO
    @Autowired
    public void init(LogAuditoriaBO bo) {
        AuditoriaListener.logBO = bo;
    }

    @PostPersist
    public void postPersist(Object entidade) {
        executar(entidade, "INSERT");
    }

    @PostUpdate
    public void postUpdate(Object entidade) {
        executar(entidade, "UPDATE");
    }

    @PreRemove
    public void preRemove(Object entidade) {
        executar(entidade, "DELETE");
    }

    private void executar(Object obj, String acao) {
        if (logBO != null) {
            try {
                LogAuditoria log = new LogAuditoria();
                log.setAcao(acao);
                log.setEntidade(obj.getClass().getSimpleName());
                log.setUsuario("Sistema");

                // Captura o ID da entidade via reflexão
                java.lang.reflect.Method method = obj.getClass().getMethod("getId");
                log.setEntidadeId((Long) method.invoke(obj));

                logBO.create(log);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
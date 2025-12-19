package com.example.sghss.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "logs_auditoria")
public class LogAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;   // "Sistema" por enquanto
    private String acao;      // INSERT, UPDATE, DELETE
    private String entidade;  // Nome da classe (Ex: Unidade)
    private Long entidadeId;  // ID do registro afetado
    private LocalDateTime dataHora;

    public LogAuditoria() {
        this.dataHora = LocalDateTime.now();
    }

  
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }
    public String getUsuario() { 
        return usuario; 
    }
    public void setUsuario(String usuario) { 
        this.usuario = usuario; 
    }
    public String getAcao() { 
        return acao; 
    }
    public void setAcao(String acao) { 
        this.acao = acao; 
    }
    public String getEntidade() { 
        return entidade; 
    }
    public void setEntidade(String entidade) { 
        this.entidade = entidade; 
    }
    public Long getEntidadeId() { 
        return entidadeId; 
    }
    public void setEntidadeId(Long entidadeId) { 
        this.entidadeId = entidadeId; 
    }
    public LocalDateTime getDataHora() { 
        return dataHora; 
    }
    public void setDataHora(LocalDateTime dataHora) { 
        this.dataHora = dataHora; 
    }
}
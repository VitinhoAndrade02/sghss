package com.example.sghss.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "log_auditoria")
public class LogAuditoria {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotBlank(message = "Informe o usuário")
    private String usuario;

    @Column(length = 500)
    @NotBlank(message = "Informe a ação")
    private String acao;

    @Column(name="data_hora", nullable=false)
    @NotNull(message = "Informe a data e hora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name="paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="profissional_id")
    private Profissional profissional;

    public Long getIdLogAuditoria() { 
        return id; 
    }
    public void setIdLogAuditoria(Long idLogAuditoria) { 
        this.id = idLogAuditoria; 
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

    public LocalDateTime getDataHora() { 
        return dataHora; 
    }
    public void setDataHora(LocalDateTime dataHora) { 
        this.dataHora = dataHora; 
    }

    public Paciente getPaciente() { 
        return paciente; 
    }
    public void setPaciente(Paciente paciente) { 
        this.paciente = paciente; 
    }

    public Profissional getProfissional() { 
        return profissional; 
    }
    public void setProfissional(Profissional profissional) {
        this.profissional = profissional; 
        }
}

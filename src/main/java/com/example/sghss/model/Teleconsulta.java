package com.example.sghss.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "teleconsultas")
public class Teleconsulta {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="paciente_id", nullable=false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="profissional_id", nullable=false)
    private Profissional profissional;

    @Column(name="data_hora", nullable=false)
    @NotNull(message = "Informe a data e hora")
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o status da teleconsulta")
    private StatusConsulta status;

    @Column(length = 500)
    private String descricao;


    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
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

    public LocalDateTime getDataHora() { 
        return dataHora; 
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora; 
    }

    public StatusConsulta getStatus() { 
        return status; 
    }
    public void setStatus(StatusConsulta status) {
        this.status = status; 
    }

    public String getDescricao() { 
        return descricao; 
    }
    public void setDescricao(String descricao) { 
        this.descricao = descricao; 
    }
}

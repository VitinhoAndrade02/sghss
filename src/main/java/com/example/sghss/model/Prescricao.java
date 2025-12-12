package com.example.sghss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "prescricoes")
public class Prescricao {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="paciente_id", nullable=false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="profissional_id", nullable=false)
    private Profissional profissional;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o tipo da prescrição")
    private TipoPrescricao tipoPrescricao;
    
    //Data da emisssão
    //Medicamentos

    @Column(length = 500)
    private String observacao;

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

    public TipoPrescricao getTipoPrescricao() { 
        return tipoPrescricao; 
    }
    public void setTipoPrescricao(TipoPrescricao tipoPrescricao) { 
        this.tipoPrescricao = tipoPrescricao; 
    }

    public String getObservacao() { 
        return observacao; 

    }
    public void setObservacao(String observacao) { 
        this.observacao = observacao; 
    }
}

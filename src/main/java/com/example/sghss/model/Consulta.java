package com.example.sghss.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "Informe o paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    @NotNull(message = "Informe o profissional")
    private Profissional profissional;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe a especialidade")
    private Especialidade especialidade;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="data_consulta", columnDefinition = "DATE")
    private LocalDate dataConsulta;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name="hora_consulta", columnDefinition = "DATE")   
    private LocalTime horaConsulta;

    @Column(name="descricao_consulta")
    private String descricaoConsulta;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o status")
    private StatusConsulta statusConsulta;
    
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
    public Especialidade getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    public LocalDate getDataConsulta() {
        return dataConsulta;
    }
    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }
    public void setHoraConsulta(LocalTime horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public String getDescricaoConsulta() {
        return descricaoConsulta;
    }

    public void setDescricaoConsulta(String descricaoConsulta) {
        this.descricaoConsulta = descricaoConsulta;
    }

    public StatusConsulta getStatusConsulta() {
        return statusConsulta;
    }
    public void setStatusConsulta(StatusConsulta statusConsulta) {
        this.statusConsulta = statusConsulta;
    }

}

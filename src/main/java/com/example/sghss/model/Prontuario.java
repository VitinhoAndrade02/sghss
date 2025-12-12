package com.example.sghss.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prontuarios")
public class Prontuario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="paciente_id", nullable=false)
    private Paciente paciente;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable=false, name="data_consulta", columnDefinition = "DATE")
    private LocalDate dataConsulta;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(nullable=false, name="hora_consulta", columnDefinition = "TIME")
    private LocalTime horaConsulta;

    @Column(length = 100)
    private String descricao;   

    
    public Long getId() {
        return id;
    }   
    
    public void setIdProntuario(Long idProntuario) {
        this.id = idProntuario;
    }

    public Paciente paciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
     public LocalDate gethoraConsulta() {
        return dataConsulta;
    }

    public void setHoraConsulta(LocalTime horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

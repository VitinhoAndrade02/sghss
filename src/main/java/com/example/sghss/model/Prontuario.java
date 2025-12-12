package com.example.sghss.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


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
    @Column(name="data_registro", columnDefinition = "DATE")
    @NotNull(message = "Informe a data do registro")
    private LocalDate dataRegistro;

    @Column(length = 100)
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

    public LocalDate getDataRegistro() { 
        return dataRegistro; 
    }

    public void setDataRegistro(LocalDate dataRegistro) { 
        this.dataRegistro = dataRegistro; 
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

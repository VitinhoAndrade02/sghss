package com.example.sghss.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prontuarios")
public class Prontuario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idProntuario;
    
    @Column(nullable = false)
    private Long idPaciente;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="data_consulta", columnDefinition = "DATE")
    private LocalDate dataConsulta;

    @Column(length = 100)
    private String descricao;   

    @Column(nullable = false)
    private boolean ativo = true;
    
    public Long getIdProntuario() {
        return idProntuario;
    }   
    
    public void setIdProntuario(Long idProntuario) {
        this.idProntuario = idProntuario;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}

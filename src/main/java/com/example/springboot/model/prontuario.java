package com.example.springboot.model;

import java.time.LocalDate;


public class prontuario {
    private Long idProntuario;
    private Long idPaciente;
    private LocalDate dataConsulta;
    private String descricao;   
    
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



}

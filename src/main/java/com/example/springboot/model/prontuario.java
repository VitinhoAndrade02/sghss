package com.example.springboot.model;

import java.util.Date;
import java.util.UUID;

public class prontuario {
    private UUID idProntuario;
    private UUID idPaciente;
    private Date dataConsulta;
    private String descricao;   
    
    public UUID getIdProntuario() {
        return idProntuario;
    }   
    public void setIdProntuario(UUID idProntuario) {
        this.idProntuario = idProntuario;
    }
    public UUID getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(UUID idPaciente) {
        this.idPaciente = idPaciente;
    }
    public Date getDataConsulta() {
        return dataConsulta;
    }
    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    


}

package com.example.springboot.model;

import java.util.Date;

public class consulta {
    private String idConsulta;
    private String idPaciente;
    private String idProfissional;
    private Date dataConsulta;
    private String horaConsulta;
    private String descricaoConsulta;

    public String getIdConsulta() {
        return idConsulta;
    }
    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }
    public String getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getIdProfissional() {
        return idProfissional;
    }
    public void setIdProfissional(String idProfissional) {
        this.idProfissional = idProfissional;
    }
    public Date getDataConsulta() {
        return dataConsulta;
    }
    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
    public String getHoraConsulta() {
        return horaConsulta;
    }
    public void setHoraConsulta(String horaConsulta) {
        this.horaConsulta = horaConsulta;
    }
    public String getDescricaoConsulta() {
        return descricaoConsulta;
    }
    public void setDescricaoConsulta(String descricaoConsulta) {
        this.descricaoConsulta = descricaoConsulta;
    }

}

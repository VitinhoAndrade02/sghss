package com.example.springboot.model;

import java.time.LocalDate;


public class consulta {
    private Long idConsulta;
    private Long idPaciente;
    private Long idProfissional;
    private LocalDate dataConsulta;
    private String horaConsulta;
    private String descricaoConsulta;

    public Long getIdConsulta() {
        return idConsulta;
    }
    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }
    public Long getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
    public Long getIdProfissional() {
        return idProfissional;
    }
    public void setIdProfissional(Long idProfissional) {
        this.idProfissional = idProfissional;
    }
    public LocalDate getDataConsulta() {
        return dataConsulta;
    }
    public void setDataConsulta(LocalDate dataConsulta) {
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

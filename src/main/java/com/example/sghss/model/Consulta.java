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
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @NotNull(message = "Informe a data da consulta")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_consulta", nullable = false)
    private LocalDate dataConsulta;

    @NotNull(message = "Informe a hora da consulta")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name = "hora_consulta", nullable = false)
    private LocalTime horaConsulta;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o tipo da consulta")
    private TipoConsulta tipoConsulta;

    @Column(name = "link_teleconsulta")
    @Size(max = 255)
    private String linkTeleconsulta;

    @Column(name = "descricao_consulta", length = 255)
    private String descricaoConsulta;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o status da consulta")
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

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(TipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getLinkTeleconsulta() {
        return linkTeleconsulta;
    }

    public void setLinkTeleconsulta(String linkTeleconsulta) {
        this.linkTeleconsulta = linkTeleconsulta;
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

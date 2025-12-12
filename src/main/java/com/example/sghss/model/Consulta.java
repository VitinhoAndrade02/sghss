package com.example.sghss.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idPaciente;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe a especialidade")
    private Especialidade especialidade;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="data_consulta", columnDefinition = "DATE")
    private LocalDate dataConsulta;

    @Column(name="hora_consulta", length = 5)   
    private String horaConsulta;

    @Column(name="descricao_consulta")
    private String descricaoConsulta;
    
    
    
    public Long getIdConsulta() {
        return id;
    }
    public void setIdConsulta(Long idConsulta) {
        this.id = idConsulta;
    }
    public Long getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
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

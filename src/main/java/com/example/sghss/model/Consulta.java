package com.example.sghss.model;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idConsulta;

    @Column(nullable = false)
    private Long idPaciente;

    @Column(nullable = false)
    private Long idProfissional;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="data_consulta", columnDefinition = "DATE")
    private LocalDate dataConsulta;

    @Column(name="hora_consulta", length = 5)   
    private String horaConsulta;

    @Column(name="descricao_consulta")
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

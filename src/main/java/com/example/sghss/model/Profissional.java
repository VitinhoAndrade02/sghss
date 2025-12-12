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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "profissionais")
public class Profissional {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =50)    
    @NotBlank(message = "Informe o nome")
    @Size(min = 3, max = 50)
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe a especialidade")
    private Especialidade especialidade;

    @Column(length = 20)
    private String crm;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="data_nascimento", columnDefinition = "DATE")
    @NotNull(message = "Informe a data de nascimento")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o sexo")
    private Sexo sexo;

    @Column(length = 15)
    private String telefone;

    @Column(length = 50)
    private String email;

    @Column(length = 100)
    private String endereco;
     
    @OneToMany(mappedBy = "profissional")
    private java.util.List<Consulta> consultas;
    

    public Long getIdProfissional() {
        return id;
    }
    public void setIdProfissional(Long idProfissional) {
        this.id = idProfissional;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Especialidade getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    public String getCrm() {
        return crm;
    }
    public void setCrm(String crm) {
        this.crm = crm;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public Sexo getSexo() {
        return sexo;
    }
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public java.util.List<Consulta> getConsultas() {
        return consultas; 
    }
    public void setConsultas(java.util.List<Consulta> consultas) {
        this.consultas = consultas; 
    }

}
package com.example.springboot.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "profissionais")
public class profissional {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idProfissional;

    @Column(nullable = false, length =50)    
    private String nome;

    @Column(length = 15)
    private String especialidade;

    @Column(length = 20)
    private String crm;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="data_nascimento", columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @Column(length = 15)
    private String telefone;

    @Column(length = 50)
    private String email;

    @Column(length = 100)
    private String endereco;


    public Long getIdProfissional() {
        return idProfissional;
    }
    public void setIdProfissional(Long idProfissional) {
        this.idProfissional = idProfissional;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
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

}
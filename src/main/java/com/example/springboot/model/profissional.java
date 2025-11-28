package com.example.springboot.model;

import java.time.LocalDate;


public class profissional {
    private Long idProfissional;
    private String nome;
    private String especialidade;
    private String crm;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
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
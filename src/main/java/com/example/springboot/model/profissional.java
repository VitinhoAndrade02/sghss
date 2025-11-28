package com.example.springboot.model;

import java.util.UUID;
import java.util.Date;

public class profissional {
    private UUID idProfissional;
    private String nome;
    private String especialidade;
    private String crm;
    private Date dataNascimento;
    private Number telefone;
    private String email;
    private String endereco;

    public UUID getIdProfissional() {
        return idProfissional;
    }
    public void setIdProfissional(UUID idProfissional) {
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
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public Number getTelefone() {
        return telefone;
    }
    public void setTelefone(Number telefone) {
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
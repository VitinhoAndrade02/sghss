package com.example.sghss.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pacientes")
public class Paciente {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =50)
    @NotBlank(message = "Informe o nome")
    @Size(min = 3, max = 50)
    private String nome;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="data_nascimento", columnDefinition = "DATE")
    @NotNull(message = "Informe a data de nascimento")
    private LocalDate dataNascimento;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o sexo")
    private Sexo sexo;

    @Column(length = 14)
    @CPF(message="CPF inválido")
    private String cpf;

    @Column(length = 15)
    private String telefone;

    @Column(length = 50)
    @Email
    private String email;

    @Column(length = 100)
    private String endereco;

    @Column(length = 500)
    private String historicoClinico;

    @Column(nullable = false)
    private boolean ativo = true;

    
    public Long getIdPaciente() {
        return id;
    }
    public void setIdPaciente(Long idPaciente) {
        this.id = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
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
    public String getHistoricoClinico() {
        return historicoClinico;
    }
    public void setHistoricoClinico(String historicoClinico) {
        this.historicoClinico = historicoClinico;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
package com.example.sghss.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@EntityListeners(AuditoriaListener.class)
@Table(name = "profissionais")
public class Profissional {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =50)    
    @NotBlank(message = "O Nome é obrigatório.")
    @Size(min = 3, max = 50, message = "O Nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe a especialidade")
    private Especialidade especialidade;

    @Column(length = 6)
    @NotBlank(message = "O CRM é obrigatório.")
    @Pattern(regexp = "\\d{4,6}", 
            message = "O CRM deve conter apenas números, entre 4 e 6 dígitos.")
    private String crm;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="data_nascimento", columnDefinition = "DATE")
    @NotNull(message = "Informe a data de nascimento.")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o sexo.")
    private Sexo sexo;

    @Column(length = 15)
    @NotBlank(message = "Informe o celular.")
    private String telefone;

    @Column(length = 50)
    @NotBlank(message = "Informe o Email.")
    private String email;

    @Column(length = 100)
    @NotBlank(message = "Informe o endereço.")
    private String endereco;
     
    @OneToMany(mappedBy = "profissional")
    private List<Consulta> consultas;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Prescricao> prescricoes;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Prontuario> prontuarios;    

    private double salario;

    private String consultorioPadrao;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public List<Consulta> getConsultas() {
        return consultas; 
    }
    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas; 
    }
    public List<Prescricao> getPrescricoes() {
        return prescricoes; 
    }
    public void setPrescricoes(List<Prescricao> prescricoes) {
        this.prescricoes = prescricoes; 
    }
        public List<Prontuario> getProntuarios() {
        return prontuarios; 
    }
    public void setProntuarios(List<Prontuario> prontuarios) {
        this.prontuarios = prontuarios; 
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getConsultorioPadrao() {
        return consultorioPadrao;
    }

    public void setConsultorioPadrao(String consultorioPadrao) {
        this.consultorioPadrao = consultorioPadrao;
    }
}
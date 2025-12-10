package com.example.sghss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "administradores")
public class Administrador {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idAdministrador;
    
    @Column(nullable = false, length =50)
    @NotBlank(message = "Informe o nome")
    @Size(min = 3, max = 50)
    private String nome;

    @Column(nullable = false)
    private boolean ativo = true;


    public Long getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Long idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}

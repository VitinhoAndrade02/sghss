package com.example.springboot.model;

public enum Funcao {
    CLIENTE("Cliente"),
    PROFISSIONAL("Profissional"),
    ADMINISTRADOR("Administrador");

    private final String descricao;

    Funcao(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return this.descricao;
    }
}



package com.example.springboot.model;

public enum funcao {
    CLIENTE("Cliente"),
    PROFISSIONAL("Profissional"),
    ADMINISTRADOR("Administrador");

    private final String descricao;

    funcao(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return this.descricao;
    }
}



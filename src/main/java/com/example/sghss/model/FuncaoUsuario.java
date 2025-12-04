package com.example.sghss.model;

public enum FuncaoUsuario {
    PACIENTE("Paciente"),
    PROFISSIONAL("Profissional"),
    ADMINISTRADOR("Administrador");

    private final String descricao;

    FuncaoUsuario(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return this.descricao;
    }
}



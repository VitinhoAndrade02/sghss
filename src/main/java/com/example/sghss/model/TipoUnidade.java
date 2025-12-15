package com.example.sghss.model;

public enum TipoUnidade {
    HOSPITALAR("Hospitalar"),
    AMBULATORIAL("Ambulatorial"),
    CLINICA("Clinica"),
    LABORATORIO("Laboratorio");

    private final String descricao;
    TipoUnidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
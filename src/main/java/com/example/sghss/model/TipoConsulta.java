package com.example.sghss.model;

public enum TipoConsulta {
    PRESENCIAL("Presencial"),
    TELECONSULTA("Teleconsulta");

    private final String descricao;
    TipoConsulta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}

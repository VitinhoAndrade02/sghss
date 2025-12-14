package com.example.sghss.model;

public enum StatusConsulta {
    AGENDADA("Agendada"),
    CANCELADA("Cancelada"),
    CONCLUIDA("Concluida");
    
    private final String descricao;

    StatusConsulta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}

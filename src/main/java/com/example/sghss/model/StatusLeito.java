package com.example.sghss.model;

public enum StatusLeito {
    OCUPADA("Ocupada"),
    LIVRE("Livre");
    
    private final String descricao;

    StatusLeito(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}


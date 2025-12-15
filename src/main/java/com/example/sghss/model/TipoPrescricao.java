package com.example.sghss.model;

public enum TipoPrescricao {
    
    MEDICAMENTOSA("Medicamentosa"),
    TERAPIA("Terapia"),
    EXAME("Exame");

    private final String descricao;
    TipoPrescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
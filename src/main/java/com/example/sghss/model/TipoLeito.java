package com.example.sghss.model;

public enum TipoLeito {
    ENFERMARIA("Enfermaria"),
    APARTAMENTO("Enfermaria"),
    UTI("Enfermaria"),
    SEMI_UTI("Enfermaria");

    private final String descricao;
    TipoLeito(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

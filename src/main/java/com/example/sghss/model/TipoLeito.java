package com.example.sghss.model;

public enum TipoLeito {
    ENFERMARIA("Enfermaria"),
    APARTAMENTO("Apartamento"),
    UTI("UTI"),
    SEMI_UTI("Semi UTI");

    private final String descricao;
    TipoLeito(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

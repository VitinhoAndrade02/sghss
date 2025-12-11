package com.example.sghss.model;

public enum Especialidade {
    CLINICO_GERAL("Clínico geral"),
    CARDIOLOGIA("Cardiologia"),
    ORTOPEDIA("Ortopedia"),
    PEDIATRIA("Pediatria"),
    NEUROLOGIA("Neurologia"),
    PSIQUIATRIA("Psiquiatria");

    private final String descricao;

    Especialidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

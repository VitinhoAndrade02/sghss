package com.example.springboot.model;

import java.util.UUID;


public class usuario {
    private UUID id;
    private String login;
    private String senha;
    private funcao funcao;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public funcao getFuncao() {
        return funcao;
    }
    public void setFuncao(funcao funcao) {
        this.funcao = funcao;
    }

    

    
}

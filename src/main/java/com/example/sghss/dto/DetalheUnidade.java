package com.example.sghss.dto;

public class DetalheUnidade {
    private Long id;
    private String nomeUnidade;
    private double receita;
    private double despesaProporcional;
    private double lucroLiquido;
   
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }
    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }
    public double getReceita() {
        return receita;
    }
    public void setReceita(double receita) {
        this.receita = receita;
    }
    public double getDespesaProporcional() {
        return despesaProporcional;
    }
    public void setDespesaProporcional(double despesaProporcional) {
        this.despesaProporcional = despesaProporcional;
    }
    public double getLucroLiquido() {
        return lucroLiquido;
    }
    public void setLucroLiquido(double lucroLiquido) {
        this.lucroLiquido = lucroLiquido;
    }

}
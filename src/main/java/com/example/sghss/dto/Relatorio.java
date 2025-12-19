package com.example.sghss.dto;

import java.util.List;

public class Relatorio {
    private int totalUnidades;
    private double receitaTotal;
    private int totalProfissionais;
    private double salarioTotal;
    private double lucroLiquidoTotal;
    private double despesaPorUnidade;
    private List<DetalheUnidade> detalhesUnidades;
    private List<DetalheProfissional> detalhesProfissionais;


    public int getTotalUnidades() {
        return totalUnidades;
    }

    public void setTotalUnidades(int totalUnidades) {
        this.totalUnidades = totalUnidades;
    }

    public double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public int getTotalProfissionais() {
        return totalProfissionais;
    }

    public void setTotalProfissionais(int totalProfissionais) {
        this.totalProfissionais = totalProfissionais;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }

    public double getLucroLiquidoTotal() {
        return lucroLiquidoTotal;
    }

    public void setLucroLiquidoTotal(double lucroLiquidoTotal) {
        this.lucroLiquidoTotal = lucroLiquidoTotal;
    }

    public double getDespesaPorUnidade() {
        return despesaPorUnidade;
    }

    public void setDespesaPorUnidade(double despesaPorUnidade) {
        this.despesaPorUnidade = despesaPorUnidade;
    }

    public List<DetalheUnidade> getDetalhesUnidades() {
        return detalhesUnidades;
    }

    public void setDetalhesUnidades(List<DetalheUnidade> detalhesUnidades) {
        this.detalhesUnidades = detalhesUnidades;
    }

    public List<DetalheProfissional> getDetalhesProfissionais() {
        return detalhesProfissionais;
    }

    public void setDetalhesProfissionais(List<DetalheProfissional> detalhesProfissionais) {
        this.detalhesProfissionais = detalhesProfissionais;
    }

}
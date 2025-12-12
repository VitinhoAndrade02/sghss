package com.example.sghss.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "relatorios_financeiros")
public class RelFinanceiro {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o tipo da transação")
    private TipoTransacao tipoTransacao;

    @Column(nullable=false)
    @NotNull(message = "Informe o valor")
    private Double valor;

    @Column(name="data_transacao", nullable=false)
    @NotNull(message = "Informe a data da transação")
    private LocalDate dataTransacao;

    @Column(length = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name="unidade_id", nullable=false)
    private Unidade unidade;

    public Long getIdRelFinanceiro() { 
        return id; 
    }
    public void setIdRelFinanceiro(Long idRelFinanceiro) { 
        this.id = idRelFinanceiro; 
    }

    public TipoTransacao getTipoTransacao() { 
        return tipoTransacao; 
    }
    public void setTipoTransacao(TipoTransacao tipoTransacao) { 
        this.tipoTransacao = tipoTransacao; }

    public Double getValor() { 
        return valor; 
    }
    public void setValor(Double valor) { 
        this.valor = valor; 
    }

    public LocalDate getDataTransacao() { 
        return dataTransacao; 
    }
    public void setDataTransacao(LocalDate dataTransacao) { 
        this.dataTransacao = dataTransacao; 
    }

    public String getDescricao() { 
        return descricao; 
    }
    public void setDescricao(String descricao) { 
        this.descricao = descricao; 
    }

    public Unidade getUnidade() { 
        return unidade; 
    }
    public void setUnidade(Unidade unidade) { 
        this.unidade = unidade; 
    }
}

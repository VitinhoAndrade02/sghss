package com.example.sghss.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@EntityListeners(AuditoriaListener.class)
@Table(name = "unidades")
public class Unidade {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Informe o nome")
    @Size(min = 3, max = 50)
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o tipo da unidade")
    private TipoUnidade tipoUnidade;

    @Column(length = 100)
    private String endereco;

    private String telefone;    

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leito> leitos;
    
    private double receita;

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getNome() { 
        return nome; 
    }
    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public TipoUnidade getTipoUnidade() {
        return tipoUnidade; 
    }
    public void setTipoUnidade(TipoUnidade tipoUnidade) {
        this.tipoUnidade = tipoUnidade; 
    }

    public String getEndereco() { 
        return endereco;
    }
    public void setEndereco(String endereco) { 
        this.endereco = endereco;
    }

    public String getTelefone() { 
        return telefone;
    }
    public void setTelefone(String telefone) { 
        this.telefone = telefone;
    }   

    public List<Leito> getLeitos() {
         return leitos; 
    }
    
    public void setLeitos(List<Leito> leitos) { 
        this.leitos = leitos; 
    }

    public double getReceita() {
        return receita;
    }

    public void setReceita(double receita) {
        this.receita = receita;
    }
}

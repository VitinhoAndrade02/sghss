package com.example.sghss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "unidade")
    private List<Leito> leitos;


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

    public List<Leito> getLeitos() {
         return leitos; 
    }
    
    public void setLeitos(List<Leito> leitos) { 
        this.leitos = leitos; 
    }
}

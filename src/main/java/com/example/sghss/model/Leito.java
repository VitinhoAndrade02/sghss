package com.example.sghss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "leitos")
public class Leito {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o tipo de leito")
    private TipoLeito tipoLeito;

    @Column(length = 20)
    private String codigo;

    @ManyToOne
    @JoinColumn(name="unidade_id", nullable=false)
    private Unidade unidade;

    //status do leito


    public Long getIdLeito() { 
        return id; 
    }
    public void setIdLeito(Long idLeito) { 
        this.id = idLeito; 
    }

    public TipoLeito getTipoLeito() { 
        return tipoLeito; 
    }
    public void setTipoLeito(TipoLeito tipoLeito) { 
        this.tipoLeito = tipoLeito; 
    }

    public String getCodigo() { 
        return codigo; 
    }
    public void setCodigo(String codigo) { 
        this.codigo = codigo; 
    }

    public Unidade getUnidade() { 
        return unidade; 
    }
    public void setUnidade(Unidade unidade) { 
        this.unidade = unidade; 
    }
}

package com.example.sghss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@EntityListeners(AuditoriaListener.class)
@Table(name = "leitos")
public class Leito {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o tipo de leito")
    private TipoLeito tipoLeito;

    @ManyToOne
    @JoinColumn(name="unidade_id", nullable=false)
    private Unidade unidade;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o status do leito")
    private StatusLeito statusLeito;

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public TipoLeito getTipoLeito() { 
        return tipoLeito; 
    }
    public void setTipoLeito(TipoLeito tipoLeito) { 
        this.tipoLeito = tipoLeito; 
    }

    public Unidade getUnidade() { 
        return unidade; 
    }
    public void setUnidade(Unidade unidade) { 
        this.unidade = unidade; 
    }
     public StatusLeito getStatusLeito() { 
        return statusLeito; 
    }
    public void setStatusLeito(StatusLeito statusLeito) { 
        this.statusLeito = statusLeito; 
    }
}

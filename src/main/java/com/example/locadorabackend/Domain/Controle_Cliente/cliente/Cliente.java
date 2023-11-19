package com.example.locadorabackend.Domain.Controle_Cliente.cliente;


import com.example.locadorabackend.Domain.Controle_Cliente.locacao.Locacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente")
public abstract class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 50)
    private String numInscricao;

    @Column(length = 50)
    private String nome;

    @Column (updatable = false)
    @Temporal (TemporalType.DATE)
    private Date dtNascimento;

    @Column(length = 50)
    private String sexo;

    @Column
    private boolean isAtivo;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Locacao> locacoes;

    @JsonProperty("tipoCliente")
    private String tipo;

    public Cliente(RequestCliente requestCliente) {
        this.numInscricao = requestCliente.numInscricao();
        this.nome = requestCliente.nome();
        this.dtNascimento = requestCliente.getDataNascimento();
        this.sexo = requestCliente.sexo();
        this.isAtivo = requestCliente.isAtivo();
        this.locacoes = requestCliente.locacoes();
        this.tipo = requestCliente.tipoCliente();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumInscricao(String numInscricao) {
        this.numInscricao = numInscricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }
}

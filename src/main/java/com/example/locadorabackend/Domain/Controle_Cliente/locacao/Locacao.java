package com.example.locadorabackend.Domain.Controle_Cliente.locacao;

import com.example.locadorabackend.Domain.Controle_Acervo.item.Item;
import com.example.locadorabackend.Domain.Controle_Cliente.cliente.Cliente;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Locacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column (updatable = false)
    @Temporal (TemporalType.DATE)
    private Date dataLocacao;

    @Temporal (TemporalType.DATE)
    private Date dataDevolucaoPrevista;

    @Column (updatable = false)
    @Temporal (TemporalType.DATE)
    private Date dataDevolucaoEfetiva;

    @Column
    private double valorCobrado;

    @Column
    private double valorMulta;

//    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idItem")
    private Item item;

    public Locacao(RequestLocacao requestLocacao, double valor, Date dtDevolucao) {
        this.dataLocacao = requestLocacao.getDataLocacao();
        this.dataDevolucaoPrevista = dtDevolucao;
        this.dataDevolucaoEfetiva = requestLocacao.getDataDevolucao();
        this.valorCobrado = valor;
        this.valorMulta = requestLocacao.valorMulta();
        this.cliente = requestLocacao.cliente();
        this.item = requestLocacao.item();
    }

    @JsonIgnoreProperties("locacoes")
    public Cliente getCliente() {
        return cliente;
    }
}

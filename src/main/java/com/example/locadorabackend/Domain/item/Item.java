package com.example.locadorabackend.Domain.item;

import com.example.locadorabackend.Domain.titulo.Titulo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 100)
    private String numSerie;

    @Column(updatable = false)
    @Temporal (TemporalType.DATE)
    private Date dataAquisicao;

    @Column(length = 100)
    private String tipoItem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTitulo")
    private Titulo titulo;

    public Item(RequestItem requestItem) {
        this.numSerie = requestItem.numSerie();
        this.dataAquisicao = requestItem.getDataAquisicao();
        this.tipoItem = requestItem.tipoItem();
        this.titulo = requestItem.titulo();
    }

    @JsonIgnoreProperties("itens")
    public Titulo getTitulo() {
        return titulo;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }
}

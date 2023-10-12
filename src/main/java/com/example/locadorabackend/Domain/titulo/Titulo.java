package com.example.locadorabackend.Domain.titulo;

import com.example.locadorabackend.Domain.actor.Actor;
import com.example.locadorabackend.Domain.classe.Classe;
import com.example.locadorabackend.Domain.diretor.Diretor;
import com.example.locadorabackend.Domain.item.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Titulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 50,nullable = false)
    private String nome;

    @Column(length = 50,nullable = false)
    private String ano;

    @Column(length = 150)
    private String sinopse;

    @Column(length = 150)
    private String categoria;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "titulo_ator",
            joinColumns =@JoinColumn(name = "tituloId"),
            inverseJoinColumns = @JoinColumn(name = "atorId")
    )
    private List<Actor> atores;

    @ManyToOne
    @JoinColumn(name = "idDiretor")
    private Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "idClasse")
    private Classe classe;

    @OneToMany(mappedBy = "titulo", fetch = FetchType.EAGER)
    private List<Item> itens;

    public Titulo(RequestTitulo requestTitulo) {
        this.nome = requestTitulo.nome();
        this.ano = requestTitulo.ano();
        this.sinopse = requestTitulo.sinopse();
        this.categoria = requestTitulo.categoria();
        this.atores = requestTitulo.atores();
        this.diretor = requestTitulo.diretor();
        this.classe = requestTitulo.classe();
        this.itens = requestTitulo.itens();
    }

}

package com.example.locadorabackend.Domain.classe;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Classe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private int id;

    @Column(length = 50)
    private String nome;
    @Column(length = 50)
    private int valor;

    @Column (updatable = false)
    @Temporal (TemporalType.DATE)
    private Date dataDevolucao;

    public Classe(String nome, int valor, Date dataDevolucao) {
        this.nome = nome;
        this.valor = valor;
        this.dataDevolucao = dataDevolucao;
    }
}

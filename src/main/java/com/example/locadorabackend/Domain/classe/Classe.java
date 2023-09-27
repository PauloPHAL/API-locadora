package com.example.locadorabackend.Domain.classe;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

@Data
@Entity()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Classe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private int id;

    private String nome;
    private int valor;

    @Column (updatable = false)
    private String dataDevolucao;

    public Classe(RequestClasse requestClasse){
        this.nome = requestClasse.nome();
        this.valor = requestClasse.valor();
        this.dataDevolucao = requestClasse.dataDevolucao();
    }


}

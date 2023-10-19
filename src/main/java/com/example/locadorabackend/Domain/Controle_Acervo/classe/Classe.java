package com.example.locadorabackend.Domain.Controle_Acervo.classe;

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
public class Classe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 50)
    private String nome;
    @Column(length = 50)
    private int valor;

    @Column (updatable = false)
    @Temporal (TemporalType.DATE)
    private Date dataDevolucao;

    public Classe(RequestClasse requestClasse) {
        this.nome = requestClasse.nome();
        this.valor = requestClasse.valor();
        this.dataDevolucao = requestClasse.getDataDevolucaoAsDate();
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}

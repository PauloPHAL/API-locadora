package com.example.locadorabackend.Domain.diretor;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Diretor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private int id;

    @Column(length = 50,nullable = false)
    private String nome;

    public Diretor(RequestDiretor requestDiretor){
        this.nome = requestDiretor.nome();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

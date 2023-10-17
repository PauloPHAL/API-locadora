package com.example.locadorabackend.Domain.actor;

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
public class Actor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 50,nullable = false)
    private String nome;

    public Actor(RequestActor requestActor){
        this.nome = requestActor.nome();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

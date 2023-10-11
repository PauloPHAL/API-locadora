package com.example.locadorabackend.Domain.titulo;

import com.example.locadorabackend.Domain.actor.Actor;
import com.example.locadorabackend.Domain.classe.Classe;
import com.example.locadorabackend.Domain.diretor.Diretor;
import com.example.locadorabackend.Domain.item.Item;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RequestTitulo(
        Long id,

        @NotBlank
        String nome,

        @NotBlank
        String ano,

        @NotBlank
        String sinopse,

        @NotBlank
        String categoria,

        List<Actor> atores,

        Diretor diretor,

        Classe classe,

        List<Item> itens
) {
}

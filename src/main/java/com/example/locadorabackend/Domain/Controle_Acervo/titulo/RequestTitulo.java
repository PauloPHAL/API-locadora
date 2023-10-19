package com.example.locadorabackend.Domain.Controle_Acervo.titulo;

import com.example.locadorabackend.Domain.Controle_Acervo.classe.Classe;
import com.example.locadorabackend.Domain.Controle_Acervo.diretor.Diretor;
import com.example.locadorabackend.Domain.Controle_Acervo.actor.Actor;
import com.example.locadorabackend.Domain.Controle_Acervo.item.Item;
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

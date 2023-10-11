package com.example.locadorabackend.Domain.item;

import com.example.locadorabackend.Domain.titulo.Titulo;
import jakarta.validation.constraints.NotBlank;

import java.text.SimpleDateFormat;
import java.util.Date;

public record RequestItem(
        Long id,

        @NotBlank
        String numSerie,

        @NotBlank
        String dataAquisicao,

        @NotBlank
        String tipoItem,

        Titulo titulo
) {
    public Date getDataAquisicao() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dataAquisicao);
        } catch (Exception e) {
            return null;
        }
    }
}

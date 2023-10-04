package com.example.locadorabackend.Domain.classe;

import jakarta.validation.constraints.NotBlank;

import java.text.SimpleDateFormat;
import java.util.Date;

public record RequestClasse(
        int id,
        @NotBlank
        String nome,
        @NotBlank
        int valor,
        @NotBlank
        String dataDevolucao
) {
    public Date getDataDevolucaoAsDate() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dataDevolucao);
        } catch (Exception e) {
            return null;
        }
    }
}

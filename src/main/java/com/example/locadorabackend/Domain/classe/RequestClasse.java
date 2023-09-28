package com.example.locadorabackend.Domain.classe;

import java.text.SimpleDateFormat;
import java.util.Date;

public record RequestClasse(
        String nome,
        int valor,
        String dataDevolucao
) {
    public Date getDataDevolucaoAsDate() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dataDevolucao);
        } catch (Exception e) {
            // Lida com erro de formatação da data, se necessário
            return null; // ou outra ação adequada
        }
    }
}

package com.example.locadorabackend.Domain.classe;

import java.util.Date;

public record RequestClasse(
        String nome,
        int valor,
        String dataDevolucao
) {
}

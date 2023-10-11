package com.example.locadorabackend.Domain.diretor;

import jakarta.validation.constraints.NotBlank;

public record RequestDiretor(
        Long id,
        @NotBlank
        String nome
) {
}

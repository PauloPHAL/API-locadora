package com.example.locadorabackend.Domain.diretor;

import jakarta.validation.constraints.NotBlank;

public record RequestDiretor(
        int id,
        @NotBlank
        String nome) {
}

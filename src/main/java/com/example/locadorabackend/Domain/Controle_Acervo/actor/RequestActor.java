package com.example.locadorabackend.Domain.Controle_Acervo.actor;

import jakarta.validation.constraints.NotBlank;

public record RequestActor (Long id,
                            @NotBlank
                            String nome
) {


}

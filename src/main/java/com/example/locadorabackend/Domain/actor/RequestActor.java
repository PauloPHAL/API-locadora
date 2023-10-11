package com.example.locadorabackend.Domain.actor;

import jakarta.validation.constraints.NotBlank;

public record RequestActor (Long id,
                            @NotBlank
                            String nome
) {


}

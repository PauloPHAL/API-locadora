package com.example.locadorabackend.Domain.actor;

import jakarta.validation.constraints.NotBlank;

public record RequestActor (int id,
                            @NotBlank
                            String nome){


}

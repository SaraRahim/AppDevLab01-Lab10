package com.example.lab5.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record PetDTO(
        @NotEmpty @NotNull String id,
        @NotEmpty @NotNull String name,
        @NotEmpty @NotNull String breed,
        @Min(value = 0, message = "Age must be above 0") int age,
        @NotEmpty @NotNull String householdEircode
) {}
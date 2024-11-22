package com.example.lab5.dto;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public record HouseholdDTO(
        @NotEmpty @NotNull String eircode,
        @NotNull Integer numberOfOccupants,
        @NotNull Boolean ownerOccupied
) {
    public String getEircode() {
        return eircode;
    }

    public int getNumberOfOccupants() {
        return numberOfOccupants;
    }

    public boolean getOwnerOccupied() {
        return ownerOccupied;
    }
}
package com.harshvardhan.ludex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


/**
 * Data Transfer Object for incoming game creation/update requests.
 * Carries only the fields the client is allowed to supply.
 */
public class GameRequestDTO {

    @NotBlank(message = "Game name is required")
    @Size(
            min = 2,
            max = 100,
            message = "Game name must be between 2 and 100 characters"
    )
    private String name;

    @NotNull(message = "Genre ID is required")
    @Positive(message = "Genre ID must be positive")
    private Integer genre_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }
}
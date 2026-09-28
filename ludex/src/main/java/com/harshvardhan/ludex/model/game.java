package com.harshvardhan.ludex.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Model class representing a Game entity.
 * Uses Lombok's {@code @Data} annotation to auto-generate
 * getters, setters, equals, hashCode, and toString methods.
 */
@Data
@Entity 
public class game {

    /** Unique identifier for the game. */
    @Id 
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int game_id;

    /** Name/title of the game. */
    @NotBlank(message = "Game name is required")
    @Size (
        min = 2,
        max = 150,
        message = "Game name must be between 2 and 100 characters"
    )
    private String game_name;

    /** A brief description of the game. */
    @NotBlank(message = "Genre is required")
    @ManyToOne 
    @JoinColumn (name = "genre_id")
    private String genre;

    private String section = "Home";

}

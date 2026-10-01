package com.harshvardhan.ludex.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int game_id;

    /** Name/title of the game. */
    @NotBlank(message = "Game name is required")
    @Size(min = 2, max = 150, message = "Game name must be between 2 and 100 characters")
    private String game_name;

    /** A brief description of the game. */
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private genre genre;

    private String section = "Home";

    @OneToOne 
    @JoinColumn (name = "details_id")
    private GameDetails gameDetails;

    @ManyToMany
@JoinTable(
        name = "game_platform",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "platform_id")
)
private List<Platform> platforms = new ArrayList<>();

}

package com.harshvardhan.ludex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String game_name;

    /** A brief description of the game. */
    private String game_description;

    private String section = "Home";

}

package com.harshvardhan.ludex.model;

import lombok.Data;

/**
 * Model class representing a Game entity.
 * Uses Lombok's {@code @Data} annotation to auto-generate
 * getters, setters, equals, hashCode, and toString methods.
 */
@Data
public class game {

    /** Unique identifier for the game. */
    private int game_id;

    /** Name/title of the game. */
    private String game_name;

    /** A brief description of the game. */
    private String game_description;

}

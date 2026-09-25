package com.harshvardhan.ludex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for outgoing game responses.
 * Exposes all relevant game fields to the client without leaking entity
 * internals.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResponseDTO {

    /** Unique identifier of the game. */
    private int game_id;

    /** Title of the game. */
    private String game_name;

    /** Short description of the game. */
    private String game_description;

    /** Current section of the game (e.g. "Home" or "Favorite"). */
    private String section;
}

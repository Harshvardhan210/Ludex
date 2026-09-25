package com.harshvardhan.ludex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object for incoming game creation/update requests.
 * Carries only the fields the client is allowed to supply.
 */
@Data
public class GameRequestDTO {

    /** Title of the game. */
    @NotBlank(message = "Game name is required")
    @Size(min = 2, max = 150, message = "Game name must be between 2 and 150 characters")
    private String game_name;

    /** Short description of the game. */
    @NotBlank(message = "Game description is required")
    private String game_description;
}

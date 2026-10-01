package com.harshvardhan.ludex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data 
public class GameDetailsDTO {

    @NotBlank (message = "Developer is required")
    private String developer;

    @Positive (message = "Release year must be positive")
    private int releaseYear;

    @NotBlank (message = "Platform is required")
    private String platform;

    
}

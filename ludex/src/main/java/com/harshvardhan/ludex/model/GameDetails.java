package com.harshvardhan.ludex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data 
@Entity 
@Table (name = "game_details")
public class GameDetails {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int details_id;

    private String developer;

    private int releaseYear;
    
    private String platform;
    
}

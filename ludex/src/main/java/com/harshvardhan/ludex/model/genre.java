package com.harshvardhan.ludex.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data 
@Entity 
@Table (name = "genres")
public class genre {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int genre_id;
    
    private String name;

    
    
}

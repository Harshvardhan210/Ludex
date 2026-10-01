package com.harshvardhan.ludex.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data 
@Entity 
@Table(name = "platforms")
public class Platform {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int Platform_id;

    private String name;


    @ManyToMany (mappedBy = "platforms")
    private List<game> games = new ArrayList<>();
    
}

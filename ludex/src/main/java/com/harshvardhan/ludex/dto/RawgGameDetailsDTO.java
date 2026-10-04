package com.harshvardhan.ludex.dto;

import lombok.Data;


@Data 
public class RawgGameDetailsDTO {

    private int id;
    private String name;
    private String description;
    private String released;
    private String background_image;
    private double rating;
    private int ratings_count;
    private int metacritic;

   
}

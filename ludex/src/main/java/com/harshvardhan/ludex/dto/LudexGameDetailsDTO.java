package com.harshvardhan.ludex.dto;

import java.util.List;

import lombok.Data;

@Data 
public class LudexGameDetailsDTO {

    private int id;
    private String name;
    private String description;
    private String released;
    private String image;
    private double rating;
    private int ratingsCount;
    private int metacritic;
    private List<RawgPlatformDTO> platform;
    
}

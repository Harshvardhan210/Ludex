package com.harshvardhan.ludex.dto;

import java.util.List;

import lombok.Data;

@Data
public class LudexGameDTO {

    private int id;
    private String name;
    private String released;
    private String image;
    private double rating;
    private List<RawgSearchPlatformDTO> platforms;
    
}

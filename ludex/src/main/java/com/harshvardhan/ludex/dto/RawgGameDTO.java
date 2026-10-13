package com.harshvardhan.ludex.dto;

import java.util.List;

import lombok.Data;

@Data
public class RawgGameDTO {
    private int id;
    private String name;
    private String released;
    private String background_image;
    private double rating;
    private List<RawgSearchPlatformDTO> platform;
    
}

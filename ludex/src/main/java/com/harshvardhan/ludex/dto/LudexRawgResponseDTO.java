package com.harshvardhan.ludex.dto;

import java.util.List;

import lombok.Data;

@Data 
public class LudexRawgResponseDTO {

    private int totalGames;
    private String nextPage;
    private String previousPage;
    private List<LudexGameDTO> games;
    
}

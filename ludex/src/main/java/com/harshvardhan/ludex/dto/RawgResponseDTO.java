package com.harshvardhan.ludex.dto;

import java.util.List;

import lombok.Data;

@Data
public class RawgResponseDTO {

    private int count;
    private String next;
    private String previous;
    private List<RawgGameDTO> results;
    
}

package com.harshvardhan.ludex.service;

import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.client.RawgClient;
import com.harshvardhan.ludex.dto.RawgResponseDTO;

@Service
public class RawgService {

    private final RawgClient rawgClient;

    public RawgService(RawgClient rawgClient) {
        this.rawgClient = rawgClient;
    }

    public RawgResponseDTO searchGames(
        String search,
        String genre,
        int page,
        int pageSize
    )
    {
        return rawgClient.getGames(
           search,
           genre,
           page,
           pageSize
        );
    }

}
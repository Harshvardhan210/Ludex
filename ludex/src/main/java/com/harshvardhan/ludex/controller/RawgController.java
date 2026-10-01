package com.harshvardhan.ludex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harshvardhan.ludex.client.RawgClient;
import com.harshvardhan.ludex.dto.RawgResponseDTO;

@RestController
@RequestMapping("/rawg")
public class RawgController {

    private final RawgClient rawgClient;

    public RawgController(RawgClient rawgClient) {
        this.rawgClient = rawgClient;
    }

@GetMapping("/games")
public RawgResponseDTO getGamesFromRawg(
        @RequestParam String search,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int pageSize) {

    return rawgClient.getGames(
            search,
            page,
            pageSize
    );
}
}
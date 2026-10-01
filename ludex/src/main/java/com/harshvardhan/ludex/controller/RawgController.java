package com.harshvardhan.ludex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshvardhan.ludex.client.RawgClient;

@RestController
@RequestMapping("/rawg")
public class RawgController {

    private final RawgClient rawgClient;

    public RawgController(RawgClient rawgClient) {
        this.rawgClient = rawgClient;
    }

    @GetMapping("/games")
    public String getGamesFromRawg() {
        return rawgClient.getGames();
    }
}
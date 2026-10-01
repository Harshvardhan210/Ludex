package com.harshvardhan.ludex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.harshvardhan.ludex.dto.RawgResponseDTO;
import com.harshvardhan.ludex.service.RawgService;

@RestController
@RequestMapping("/rawg")
public class RawgController {

    private final RawgService rawgService;

    public RawgController(RawgService rawgService) {
        this.rawgService = rawgService;
    }

@GetMapping("/games")
public RawgResponseDTO getGamesFromRawg(
        @RequestParam String search,
        @RequestParam (required = false) String genre,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int pageSize) {

    return rawgService.searchGames(
            search,
            genre,
            page,
            pageSize
    );
}
}
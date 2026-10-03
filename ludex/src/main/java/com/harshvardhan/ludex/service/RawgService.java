package com.harshvardhan.ludex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.harshvardhan.ludex.client.RawgClient;
import com.harshvardhan.ludex.dto.LudexGameDTO;
import com.harshvardhan.ludex.dto.LudexGameDetailsDTO;
import com.harshvardhan.ludex.dto.LudexRawgResponseDTO;
import com.harshvardhan.ludex.dto.RawgGameDTO;
import com.harshvardhan.ludex.dto.RawgGameDetailsDTO;
import com.harshvardhan.ludex.dto.RawgResponseDTO;

@Service
public class RawgService {

    private final RawgClient rawgClient;

    public RawgService(RawgClient rawgClient) {
        this.rawgClient = rawgClient;
    }

    public LudexRawgResponseDTO searchGames(
            String search,
            String genre,
            int page,
            int pageSize) {

        RawgResponseDTO rawgResponse = rawgClient.getGames(
                search,
                genre,
                page,
                pageSize);

        List<LudexGameDTO> games = new ArrayList<>();

        for (RawgGameDTO rawgGame : rawgResponse.getResults()) {

            LudexGameDTO game = new LudexGameDTO();

            game.setId(rawgGame.getId());
            game.setName(rawgGame.getName());
            game.setReleased(rawgGame.getReleased());
            game.setImage(rawgGame.getBackground_image());
            game.setRating(rawgGame.getRating());

            games.add(game);
        }

        LudexRawgResponseDTO response = new LudexRawgResponseDTO();

        response.setTotalGames(rawgResponse.getCount());
        response.setNextPage(rawgResponse.getNext());
        response.setPreviousPage(rawgResponse.getPrevious());
        response.setGames(games);

        return response;
    }

  public LudexGameDetailsDTO getGameDetails(int id) {

    RawgGameDetailsDTO rawgGame =
            rawgClient.getGameDetails(id);

    LudexGameDetailsDTO game =
            new LudexGameDetailsDTO();

    game.setId(rawgGame.getId());
    game.setName(rawgGame.getName());
    game.setDescription(rawgGame.getDescription());
    game.setReleased(rawgGame.getReleased());
    game.setImage(rawgGame.getBackground_image());
    game.setRating(rawgGame.getRating());
    game.setRatingsCount(rawgGame.getRatings_count());
    game.setMetacritic(rawgGame.getMetacritic());

    return game;
}
}
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
import com.harshvardhan.ludex.dto.RawgPlatformDTO;
import com.harshvardhan.ludex.dto.RawgPlatformWrapperDTO;
import com.harshvardhan.ludex.dto.RawgResponseDTO;
import com.harshvardhan.ludex.dto.RawgSearchPlatformDTO;

@Service
public class RawgService {

    private final RawgClient rawgClient;

    public RawgService(RawgClient rawgClient) {
        this.rawgClient = rawgClient;
    }

    // Search games with pagination and optional genre
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

        if (rawgResponse.getResults() != null) {

            for (RawgGameDTO rawgGame : rawgResponse.getResults()) {

                LudexGameDTO game = new LudexGameDTO();

                game.setId(rawgGame.getId());
                game.setName(rawgGame.getName());
                game.setReleased(rawgGame.getReleased());
                game.setImage(rawgGame.getBackground_image());
                game.setRating(rawgGame.getRating());

                // Map platform information
                List<RawgSearchPlatformDTO> platforms = new ArrayList<>();

                if (rawgGame.getPlatform() != null) {

                    for (RawgSearchPlatformDTO rawgPlatform : rawgGame.getPlatform()) {

                        if (rawgPlatform != null) {

                            RawgSearchPlatformDTO platform = new RawgSearchPlatformDTO();

                            platform.setId(
                                    rawgPlatform.getId());

                            platform.setName(
                                    rawgPlatform.getName());

                            platforms.add(platform);
                        }
                    }
                }

                game.setPlatforms(platforms);
                games.add(game);
            }
        }

        LudexRawgResponseDTO response = new LudexRawgResponseDTO();

        response.setTotalGames(rawgResponse.getCount());
        response.setNextPage(rawgResponse.getNext());
        response.setPreviousPage(rawgResponse.getPrevious());
        response.setGames(games);

        return response;
    }

    // Get details of one game by RAWG ID
    public LudexGameDetailsDTO getGameDetails(int id) {

        RawgGameDetailsDTO rawgGame = rawgClient.getGameDetails(id);

        LudexGameDetailsDTO game = new LudexGameDetailsDTO();

        game.setId(rawgGame.getId());
        game.setName(rawgGame.getName());
        game.setDescription(rawgGame.getDescription());
        game.setReleased(rawgGame.getReleased());
        game.setImage(rawgGame.getBackground_image());
        game.setRating(rawgGame.getRating());
        game.setRatingsCount(rawgGame.getRatings_count());
        game.setMetacritic(rawgGame.getMetacritic());

        // Map platform information
        List<RawgPlatformDTO> platforms = new ArrayList<>();

        if (rawgGame.getPlatforms() != null) {

            for (RawgPlatformWrapperDTO wrapper : rawgGame.getPlatforms()) {

                if (wrapper != null
                        && wrapper.getPlatform() != null) {

                    RawgPlatformDTO platform = new RawgPlatformDTO();

                    platform.setId(
                            wrapper.getPlatform().getId());

                    platform.setName(
                            wrapper.getPlatform().getName());

                    platforms.add(platform);
                }
            }
        }

        game.setPlatforms(platforms);

        return game;
    }
}
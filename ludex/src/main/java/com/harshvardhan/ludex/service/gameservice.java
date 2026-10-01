package com.harshvardhan.ludex.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.dto.GameRequestDTO;
import com.harshvardhan.ludex.dto.GameResponseDTO;
import com.harshvardhan.ludex.exception.GameNotFoundException;
import com.harshvardhan.ludex.model.game;
import com.harshvardhan.ludex.model.genre;
import com.harshvardhan.ludex.repository.GameDetailsRepository;
import com.harshvardhan.ludex.repository.GameRepository;
import com.harshvardhan.ludex.repository.GenreRepository;
import com.harshvardhan.ludex.repository.platformRepository;
import com.harshvardhan.ludex.exception.GenreNotFoundException;
import com.harshvardhan.ludex.model.GameDetails;
import com.harshvardhan.ludex.model.Platform;;

/**
 * Service class containing the business logic for game management.
 * Accepts {@link GameRequestDTO} for writes and returns {@link GameResponseDTO}
 * for reads.
 */
@Service
public class gameservice {

    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;
    private final GameDetailsRepository gameDetailsRepository;
    private final platformRepository platformRepository;

    public gameservice(
            GameRepository gameRepository,
            GenreRepository genreRepository,
            GameDetailsRepository gameDetailsRepository,
            platformRepository platformRepository) {

        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
        this.gameDetailsRepository = gameDetailsRepository;
        this.platformRepository = platformRepository;
    }

    // CREATE
    public GameResponseDTO addGame(GameRequestDTO dto) {

        game g = toEntity(dto);

        game savedGame = gameRepository.save(g);

        return toResponse(savedGame);
    }

    // GET ALL
    public List<GameResponseDTO> getAllGames() {

        return gameRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // GET BY ID
    public GameResponseDTO getGameById(int id) {

        game g = gameRepository.findById(id)
                .orElseThrow(() ->
                        new GameNotFoundException(
                                "Game with ID " + id + " not found"
                        ));

        return toResponse(g);
    }

    // UPDATE
    public GameResponseDTO updateGame(
            int id,
            GameRequestDTO dto) {

        game existingGame = gameRepository.findById(id)
                .orElseThrow(() ->
                        new GameNotFoundException(
                                "Game with ID " + id + " not found"
                        ));

        existingGame.setGame_name(dto.getName());

        // Find genre
        genre gameGenre = genreRepository.findById(dto.getGenre_id())
                .orElseThrow(() ->
                        new GenreNotFoundException(
                                "Genre with ID " +
                                dto.getGenre_id() +
                                " not found"
                        ));

        existingGame.setGenre(gameGenre);

        // Update GameDetails
        if (dto.getGameDetails() != null) {

            GameDetails details = existingGame.getGameDetails();

            if (details == null) {
                details = new GameDetails();
            }

            details.setDeveloper(
                    dto.getGameDetails().getDeveloper()
            );

            details.setReleaseYear(
                    dto.getGameDetails().getReleaseYear()
            );

            details.setPlatform(
                    dto.getGameDetails().getPlatform()
            );

            GameDetails savedDetails =
                    gameDetailsRepository.save(details);

            existingGame.setGameDetails(savedDetails);
        }

        // Update platforms
        if (dto.getPlatform_ids() != null) {

            List<Platform> platforms =
                    platformRepository.findAllById(
                            dto.getPlatform_ids()
                    );

            existingGame.setPlatforms(platforms);
        }

        game updatedGame = gameRepository.save(existingGame);

        return toResponse(updatedGame);
    }

    // DTO → ENTITY
    private game toEntity(GameRequestDTO dto) {

        game g = new game();

        g.setGame_name(dto.getName());

        // Find genre
        genre gameGenre = genreRepository.findById(dto.getGenre_id())
                .orElseThrow(() ->
                        new GenreNotFoundException(
                                "Genre with ID " +
                                dto.getGenre_id() +
                                " not found"
                        ));

        g.setGenre(gameGenre);

        // New games start in Home
        g.setSection("Home");

        // Create GameDetails
        if (dto.getGameDetails() != null) {

            GameDetails details = new GameDetails();

            details.setDeveloper(
                    dto.getGameDetails().getDeveloper()
            );

            details.setReleaseYear(
                    dto.getGameDetails().getReleaseYear()
            );

            details.setPlatform(
                    dto.getGameDetails().getPlatform()
            );

            GameDetails savedDetails =
                    gameDetailsRepository.save(details);

            g.setGameDetails(savedDetails);
        }

        // Add platforms
        if (dto.getPlatform_ids() != null) {

            List<Platform> platforms =
                    platformRepository.findAllById(
                            dto.getPlatform_ids()
                    );

            g.setPlatforms(platforms);
        }

        return g;
    }

    // ENTITY → DTO
    private GameResponseDTO toResponse(game g) {

        return new GameResponseDTO(
                g.getGame_id(),
                g.getGame_name(),
                g.getGenre().getGenre_id(),
                g.getGenre().getName(),
                g.getSection()
        );
    }
}
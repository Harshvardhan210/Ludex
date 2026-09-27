package com.harshvardhan.ludex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.dto.GameRequestDTO;
import com.harshvardhan.ludex.dto.GameResponseDTO;
import com.harshvardhan.ludex.exception.GameNotFoundException;
import com.harshvardhan.ludex.model.game;
import com.harshvardhan.ludex.repository.GameRepository;

/**
 * Service class containing the business logic for game management.
 * Accepts {@link GameRequestDTO} for writes and returns {@link GameResponseDTO}
 * for reads.
 */
@Service
public class gameservice {

    private final GameRepository gameRepository;

    public gameservice(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // ------------------------------------------------------------------ //
    // Mapper helpers //
    // ------------------------------------------------------------------ //

    /**
     * Maps a {@link game} entity to a {@link GameResponseDTO}.
     */
    private GameResponseDTO toResponseDTO(game g) {
        return new GameResponseDTO(
                g.getGame_id(),
                g.getGame_name(),
                g.getGame_description(),
                g.getSection());
    }

    /**
     * Maps a {@link GameRequestDTO} to a {@link game} entity.
     */
    private game toEntity(GameRequestDTO dto) {
        game g = new game();
        g.setGame_name(dto.getGame_name());
        g.setGame_description(dto.getGame_description());
        return g;
    }

    // ------------------------------------------------------------------ //
    // Service methods //
    // ------------------------------------------------------------------ //

    /**
     * Returns all games as response DTOs.
     *
     * @return A list of {@link GameResponseDTO} objects representing all games
     */
    public List<GameResponseDTO> getallgames() {
        return gameRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Persists a new game from the request DTO and returns the saved game as a
     * response DTO.
     *
     * @param dto the request payload containing game details
     * @return the saved game mapped to a {@link GameResponseDTO}
     */
    public GameResponseDTO addGames(GameRequestDTO dto) {
        game saved = gameRepository.save(toEntity(dto));
        return toResponseDTO(saved);
    }

    /**
     * Finds a game by ID and returns it as a response DTO.
     *
     * @param id the ID of the game to find
     * @return the game mapped to a {@link GameResponseDTO}
     * @throws GameNotFoundException if no game with {@code id} exists
     */
    public GameResponseDTO findgame(int id) {
        game g = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found"));
        return toResponseDTO(g);
    }

    /**
     * Deletes the game with the given ID.
     *
     * @param id the ID of the game to delete
     * @return {@code true} if the game existed and was deleted, {@code false}
     *         otherwise
     */
    public boolean deleteGame(int id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
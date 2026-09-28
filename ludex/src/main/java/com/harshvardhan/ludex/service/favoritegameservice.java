package com.harshvardhan.ludex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.dto.GameResponseDTO;
import com.harshvardhan.ludex.exception.GameNotFoundException;
import com.harshvardhan.ludex.model.game;
import com.harshvardhan.ludex.repository.GameRepository;

/**
 * Service class handling the business logic for managing favorite games.
 * Provides functionality to add, retrieve and remove games from favorites.
 */
@Service
public class favoritegameservice {

    private final GameRepository gameRepository;

    public favoritegameservice(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // ------------------------------------------------------------------ //
    // Mapper helper //
    // ------------------------------------------------------------------ //

    private GameResponseDTO toResponseDTO(game g) {
        return new GameResponseDTO(
                g.getGame_id(),
                g.getGame_name(),
                g.getGenre(),
                g.getSection());
    }

    // ------------------------------------------------------------------ //
    // Service methods //
    // ------------------------------------------------------------------ //

    /**
     * Marks the game with the given ID as a Favorite.
     *
     * @param id the ID of the game to mark as favorite
     * @return a success string if the game is marked as favorite
     * @throws GameNotFoundException if the game does not exist
     */
    public String addfavorites(int id) {
        game g = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + id + " not found"));
        g.setSection("Favorite");
        gameRepository.save(g);
        return "Success";
    }

    /**
     * Returns all games currently in the Favorite section.
     *
     * @return A list of {@link GameResponseDTO} representing all favorite games
     */
    public List<GameResponseDTO> getallfavorite() {
        return gameRepository.findBySection("Favorite")
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Moves the game back to the "Home" section (removes from favorites).
     *
     * @param id the ID of the game to remove from favorites
     * @return {@code true} if successful, {@code false} if not found or not in
     *         Favorites
     */
    public boolean deletefavgame(int id) {
        game g = gameRepository.findById(id).orElse(null);

        if (g == null) {
            return false;
        }

        if (!"Favorite".equals(g.getSection())) {
            return false;
        }

        g.setSection("Home");
        gameRepository.save(g);
        return true;
    }
}

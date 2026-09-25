package com.harshvardhan.ludex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.dto.GameResponseDTO;
import com.harshvardhan.ludex.exception.GameNotFoundException;
import com.harshvardhan.ludex.model.game;
import com.harshvardhan.ludex.repository.GameRepository;

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
                g.getGame_description(),
                g.getSection());
    }

    // ------------------------------------------------------------------ //
    // Service methods //
    // ------------------------------------------------------------------ //

    /**
     * Marks the game with the given ID as a Favorite.
     *
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

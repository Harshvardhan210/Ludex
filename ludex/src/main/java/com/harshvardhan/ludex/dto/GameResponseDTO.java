package com.harshvardhan.ludex.dto;

public class GameResponseDTO {

    public GameResponseDTO(
            int game_id,
            String game_name,
            int genre_id,
            String genre,
            String section) {
        this.game_id = game_id;
        this.game_name = game_name;
        this.genre_id = genre_id;
        this.genre = genre;
        this.section = section;
    }

    /** Unique identifier of the game. */
    private int game_id;

    /** Title of the game. */
    private String game_name;

    /** Short description of the game. */
    private int genre_id;

    private String genre;

    /** Current section of the game (e.g. "Home" or "Favorite"). */
    private String section;

    // --- Getters ---

    public int getGame_id() {
        return game_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public String getGenre() {
        return genre;
    }

    public String getSection() {
        return section;
    }

    // --- Setters ---

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSection(String section) {
        this.section = section;
    }

}

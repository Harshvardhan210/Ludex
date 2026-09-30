package com.harshvardhan.ludex.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "genres")
public class genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genre_id;

    private String name;

    @OneToMany (mappedBy = "genre")
    private List<game> games = new ArrayList<>();

    public genre() {
    }

    public genre(String name) {
        this.name = name;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<game> getGames(){
        return games;
    }

    public void setGames(List<game> games){
        this.games = games;
    }
}

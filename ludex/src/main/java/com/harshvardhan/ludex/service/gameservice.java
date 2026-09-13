package com.harshvardhan.ludex.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.model.game;

@Service 
public class gameservice {

  public List<game> games = new ArrayList<>();

  public List<game> getAllGames(){
     return games;
    }

  public game addgames(game g){
    games.add(g);
    return g;
  }
}

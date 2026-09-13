package com.harshvardhan.ludex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshvardhan.ludex.model.game;
import com.harshvardhan.ludex.service.gameservice;

@RestController
@RequestMapping("/game")
public class gamecontroller {

    @Autowired
    private gameservice gameservice;

    @GetMapping ("/allgames")
    public List<game> getallgames(){
      return  gameservice.getAllGames();
    }

    @PostMapping("/addgame")
    public game addgames(@RequestBody game game){
        return gameservice.addgames(game);

    }

}

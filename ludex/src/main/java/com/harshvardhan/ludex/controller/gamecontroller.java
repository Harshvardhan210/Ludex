package com.harshvardhan.ludex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private  gameservice gameservice;

    @GetMapping ("/getallgames")
    public List<game> getallgames(){
      return gameservice.getAllgame();
    }

    @PostMapping("/addgame")
    public game addgames(@RequestBody game g){
      return gameservice.addGames(g);
    }

    @DeleteMapping ("/{id}")
    public String deletgams(@PathVariable int id){
        boolean deleted = gameservice.deletegames(id);

        if(deleted){
            return "Game is Deletd";
        }
        return "failed";
}

}

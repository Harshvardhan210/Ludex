package com.harshvardhan.ludex.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshvardhan.ludex.model.genre;
import com.harshvardhan.ludex.service.GenreService;

@RestController 
@RequestMapping ("/genres")
public class GenreController {
    
    private final GenreService genreService;

    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @PostMapping 
    public genre addGenre(@RequestBody genre g){
        return genreService.addGenre(g);
    }

    @GetMapping 
    public List<genre> getAllGenres(){
        return genreService.getAllGenres();
    }
}

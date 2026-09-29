package com.harshvardhan.ludex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.model.genre;
import com.harshvardhan.ludex.repository.GenreRepository;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public genre addGenre(genre g){
        return genreRepository.save(g);
    }

    public List<genre> getAllGenres(){
        return genreRepository.findAll();
    }
    
}

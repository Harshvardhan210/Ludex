package com.harshvardhan.ludex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.model.Platform;
import com.harshvardhan.ludex.repository.platformRepository;

@Service 
public class platformService {

    private final platformRepository platformRepository;

    public platformService(platformRepository platformRepository){
        this.platformRepository = platformRepository;
    }

    public Platform addPlatform(Platform platform){
       return platformRepository.save(platform);    
    }

    public List<Platform> getAllPlatforms(){
        return platformRepository.findAll();
    }
    
}

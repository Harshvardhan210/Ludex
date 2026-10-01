package com.harshvardhan.ludex.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RawgClient {

    @Value ("${rawg.api.key}")
    private String apikey;

     @Value("${rawg.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getGames(){
        String url = apiUrl
            + "/games"
            + "?key=" + apikey;

        return restTemplate.getForObject(url, String.class);
    }
    
}

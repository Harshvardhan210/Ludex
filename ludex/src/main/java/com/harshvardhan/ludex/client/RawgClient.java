package com.harshvardhan.ludex.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.harshvardhan.ludex.dto.RawgResponseDTO;

@Service
public class RawgClient {

    @Value("${rawg.api.key}")
    private String apiKey;

    @Value("${rawg.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public RawgResponseDTO getGames(
            String search,
            int page,
            int pageSize) {

        String url = UriComponentsBuilder
                .fromUriString(apiUrl + "/games")
                .queryParam("key", apiKey)
                .queryParam("search", search)
                .queryParam("page", page)
                .queryParam("page_size", pageSize)
                .toUriString();

        return restTemplate.getForObject(
                url,
                RawgResponseDTO.class);
    }
}
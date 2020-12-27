package com.musicinfofinder.musicdetectorsrv.services.player;

import com.musicinfofinder.musicdetectorsrv.models.request.player.CurrentPlayingRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.player.CurrentPlayingRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.response.player.CurrentPlaying;
import com.musicinfofinder.musicdetectorsrv.services.authorization.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PlayerServiceImpl implements IPlayerService {
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CurrentPlaying getCurrentPlaying(String userId) {
        log.info("Starting getCurrentPlaying with userId {}", userId);
        String token = tokenService.getTokenForUser(userId);
        CurrentPlayingRequest request = CurrentPlayingRequestBuilder.getRequestBuilder(token)
                .build();
        log.info("Calling /current-playing with request {}", request);
        HttpEntity<Object> requestEntity = new HttpEntity<>(request.getBody(), request.getHeaders());
        ResponseEntity<CurrentPlaying> responseEntity = restTemplate.exchange(request.getUri(), HttpMethod.GET,
                requestEntity, CurrentPlaying.class);

        log.info("Finishing getCurrentPlaying with userId {}", userId);
        return responseEntity.getBody();
    }
}

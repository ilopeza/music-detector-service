package com.musicinfofinder.musicdetectorsrv.services.player;

import com.musicinfofinder.musicdetectorsrv.models.request.player.CurrentPlayingRequest;
import com.musicinfofinder.musicdetectorsrv.models.request.player.CurrentPlayingRequestBuilder;
import com.musicinfofinder.musicdetectorsrv.models.response.player.CurrentPlaying;
import com.musicinfofinder.musicdetectorsrv.services.authorization.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerServiceImpl implements IPlayerService {
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CurrentPlaying getCurrentPlaying(String userId) {
        String token = tokenService.getTokenForUser(userId);
        CurrentPlayingRequest request = CurrentPlayingRequestBuilder.getRequestBuilder(token)
                .build();

        HttpEntity<Object> requestEntity = new HttpEntity<>(request.getBody(), request.getHeaders());
        ResponseEntity<CurrentPlaying> responseEntity = restTemplate.exchange(request.getUri(), HttpMethod.GET,
                requestEntity, CurrentPlaying.class);

        return responseEntity.getBody();
    }
}

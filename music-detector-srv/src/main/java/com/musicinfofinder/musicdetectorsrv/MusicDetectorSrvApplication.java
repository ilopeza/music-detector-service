package com.musicinfofinder.musicdetectorsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@EnableCaching
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MusicDetectorSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicDetectorSrvApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
    }

}

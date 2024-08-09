package com.Device.Manager.Device.Manager.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public String sendMessage(String httpLocation, String jsonMsg){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(jsonMsg, headers);
        ResponseEntity<String> response = this.restTemplate.exchange(
                httpLocation,
                HttpMethod.POST,
                entity,
                String.class
        );
        return response.getBody();
    }
}

package com.chathura.planner.travelplanner.client;

import com.chathura.planner.travelplanner.exception.APIException;
import com.chathura.planner.travelplanner.exception.NotFoundException;
import com.chathura.planner.travelplanner.model.response.WeatherDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
@Slf4j
public class WeatherAPIClient {

    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.endpoint}")
    private String endpoint;

    public WeatherAPIClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WeatherDetails doGet(String url, List<Object> parameters) {
        try {
            parameters.add(apiKey);
            String urlFinal = String.format(endpoint.concat(url), parameters.toArray());
            ResponseEntity<WeatherDetails> weatherDetails = restTemplate.getForEntity(urlFinal, WeatherDetails.class);
            if (weatherDetails.getStatusCode() != HttpStatus.OK) {
                log.error("Error calling to whether endpoint {} ", urlFinal);
                throw new APIException("Error calling to weather endpoint");
            }
            log.info("Response received from weather API  {} ", weatherDetails.getBody());
            return weatherDetails.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException(e);
            }
            log.error("HttpClientErrorException while calling to whether endpoint {} ,{} ", url, parameters, e);
            throw new APIException("Error calling to weather endpoint", e);
        } catch (Exception e) {
            log.error("Error while calling to whether endpoint {} ,{} ", url, parameters, e);
            throw new APIException("Error calling to weather endpoint", e);
        }

    }

}

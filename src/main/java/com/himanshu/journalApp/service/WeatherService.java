package com.himanshu.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import api.response.WeatherResponse;

@Component
public class WeatherService {
	private static final String apiKey = "9508ac1cb09c5ed43317b5414e49492d";
	private static final String API =
			"http://api.weatherstack.com/current?access_key=API&query=CITY";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public WeatherResponse getWeather(String city) {
		String finalAPI = API.replace("CITY", city).replace("API", apiKey);
		ResponseEntity<WeatherResponse> response = restTemplate
				.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
		WeatherResponse body = response.getBody();
		return body;
	}
	
}

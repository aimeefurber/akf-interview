package zipcode;

import org.springframework.web.client.RestTemplate;

public class OpenWeather {
    final String weatherURL = "https://api.openweathermap.org/data/2.5/weather?" +
            "zip={zipCode},us&units=imperial&appid={key}";

    public WeatherResponse getWeather(String zipCode, RestTemplate restTemplate, String key) {
        return restTemplate.getForObject(weatherURL, WeatherResponse.class, zipCode, key);
    }
}

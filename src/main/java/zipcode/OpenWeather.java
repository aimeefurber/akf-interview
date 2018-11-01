package zipcode;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeather {
    public static String weatherURL = "https://api.openweathermap.org/data/2.5/weather?" +
            "zip={zipCode},us&units=imperial&appid={key}";

    public WeatherResponse getWeather(String zipCode, RestTemplate restTemplate, String key) {
        return restTemplate.getForObject(weatherURL, WeatherResponse.class, zipCode, key);
    }

    public String getTemperature(WeatherResponse weather) {
        return weather.getWeatherDetails().getTemperature();
    }
}

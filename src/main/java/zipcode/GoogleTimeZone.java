package zipcode;

import org.springframework.web.client.RestTemplate;

public class GoogleTimeZone {
    final String timezoneURL = "https://maps.googleapis.com/maps/api/timezone/json?" +
            "location={lat},{lng}&timestamp={timestamp}&key={key}";

    public TimeZone getTimeZone(String lat, String lng, String timestamp, RestTemplate restTemplate, String key) {
        return restTemplate.getForObject(timezoneURL, TimeZone.class, lat, lng, timestamp, key);
    }
}

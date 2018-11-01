package zipcode;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleTimeZone {
    final String timezoneURL = "https://maps.googleapis.com/maps/api/timezone/json?" +
            "location={lat},{lng}&timestamp={timestamp}&key={key}";

    public TimeZone getTimeZone(String lat, String lng, RestTemplate restTemplate, String key) {
        return restTemplate.getForObject(timezoneURL, TimeZone.class, lat, lng, getTimestamp(), key);
    }

    public String getTimestamp() {
        final Long timestamp = System.currentTimeMillis() / 1000L;
        return timestamp.toString();
    }
}

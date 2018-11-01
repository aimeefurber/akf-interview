package zipcode;

import org.springframework.web.client.RestTemplate;

public class GoogleElevation {
    final static String elevationURL = "https://maps.googleapis.com/maps/api/elevation/json?" +
            "locations={lat},{lng}&key={key}";

    public ElevationResponse getElevation(String lat, String lng, RestTemplate restTemplate, String key) {
        return restTemplate.getForObject(elevationURL, ElevationResponse.class, lat, lng, key);
    }
}

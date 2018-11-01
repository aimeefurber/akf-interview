package zipcode;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleElevation {
    final static String elevationURL = "https://maps.googleapis.com/maps/api/elevation/json?" +
            "locations={lat},{lng}&key={key}";

    public Long getElevation(String lat, String lng, RestTemplate restTemplate, String key) {
        ElevationResponse elevation = restTemplate.getForObject(elevationURL, ElevationResponse.class, lat, lng, key);
        long roundedElevation = Math.round(elevation.getResults()[0].getElevation());
        return roundedElevation;
    }
}

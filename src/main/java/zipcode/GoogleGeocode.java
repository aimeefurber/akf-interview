package zipcode;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleGeocode {
    final static String geocodeURL = "https://maps.googleapis.com/maps/api/geocode/json?" +
            "address={zipCode}&key={key}";


    public GeocodeResponse getGeoCode(String zipCode, RestTemplate restTemplate, String key) {
        return restTemplate.getForObject(geocodeURL, GeocodeResponse.class, zipCode, key);
    }

    public String getLat(GeocodeResponse geocode) {
        return geocode.getResults()[0].getGeometry().getLocation().getLat();
    }

    public String getlng(GeocodeResponse geocode) {
        return geocode.getResults()[0].getGeometry().getLocation().getLng();
    }
}

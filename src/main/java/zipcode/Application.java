package zipcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Value("${weather.api.key}")
    String weatherKey;

    @Value("${google.api.key}")
    String key;

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            log.info("THIS IS THE KEY " + key);
//            final String key = "AIzaSyCpuimIRcnwjKDVOzU0lxtHjswk9zXKsX0";
            final String zipCode = "97701";

            //GOOGLE GEOCODE//
            final String geocodeURL = "https://maps.googleapis.com/maps/api/geocode/json?" +
                    "address={zipCode}&key={key}";
            GeocodeResponse geocode = restTemplate.getForObject(geocodeURL, GeocodeResponse.class, zipCode, key);
            log.info("GEOCODE: " + geocode.toString());
            log.info("LAT: " + geocode.getResults()[0].getGeometry().getLocation().getLat());
            log.info("LON: " + geocode.getResults()[0].getGeometry().getLocation().getLng());
            final String lat = geocode.getResults()[0].getGeometry().getLocation().getLat();
            final String lng = geocode.getResults()[0].getGeometry().getLocation().getLng();

            // GOOGLE TIMEZONE //
//            final String location = "38.908133,-77.047119";
            final String location = lat + "," + lng;
            final Long nowMS = System.currentTimeMillis() / 1000L;
            log.info("NOW: " + nowMS);
            final String timestamp = nowMS.toString();
            final String timezoneURL = "https://maps.googleapis.com/maps/api/timezone/json?" +
                    "location={location}&timestamp={timestamp}&key={key}";
//            final String timezoneURL = "https://maps.googleapis.com/maps/api/timezone/json?" +
//                    "location=38.908133,-77.047119&timestamp=1458000000&key={key}";
//            TimeZone response = restTemplate.getForObject(uri, TimeZone.class, key);
            TimeZone timeZone = restTemplate.getForObject(timezoneURL, TimeZone.class, location, timestamp, key);
            log.info(timeZone.toString());
            log.info("TIME ZONE NAME: " + timeZone.getTimeZoneName());

            // GOOGLE ELEVATION //
            final String elevationURL = "https://maps.googleapis.com/maps/api/elevation/json?" +
                    "locations={location}&key={key}";
            ElevationResponse elevation = restTemplate.getForObject(elevationURL, ElevationResponse.class, location, key);
            long roundedElevation = Math.round(elevation.getResults()[0].getElevation());
            log.info("ELEVATION:" + roundedElevation);

            // OPEN WEATHER //
//            final String weatherKey = "b48966559806edfd70c71b835fa1a945";
            final String weatherURL = "https://api.openweathermap.org/data/2.5/weather?" +
                    "zip={zipCode},us&units=imperial&appid={weatherKey}";
            WeatherResponse weather = restTemplate.getForObject(weatherURL, WeatherResponse.class, zipCode, weatherKey);
            log.info("CITY: " + weather.getName());
            log.info("TEMP: " + weather.getWeatherDetails().getTemperature());

        };
    }
}

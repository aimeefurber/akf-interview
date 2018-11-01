package zipcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
//            Quote quote = restTemplate.getForObject(
//                    "http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//            log.info(quote.toString());
            final String key = "AIzaSyCpuimIRcnwjKDVOzU0lxtHjswk9zXKsX0";
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

            // GOOGLE ELEVATION //
            final String elevationURL = "https://maps.googleapis.com/maps/api/elevation/json?" +
                    "locations={location}&key={key}";
            ElevationResponse elevation = restTemplate.getForObject(elevationURL, ElevationResponse.class, location, key);
            log.info("ELEVATION:" + elevation.getResults()[0].getElevation());

            // OPEN WEATHER //
            final String weather_key = "b48966559806edfd70c71b835fa1a945";
            final String weatherURL = "https://api.openweathermap.org/data/2.5/weather?" +
                    "zip={zipCode},us&units=imperial&appid={weather_key}";
            WeatherResponse weather = restTemplate.getForObject(weatherURL, WeatherResponse.class, zipCode, weather_key);
            log.info("WEATHER: " + weather);

        };
    }
}

package zipcode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class GoogleTimeZone {
    private static final String timezone_url = "https://maps.googleapis.com/"
            + "maps/api/place/details/json?reference="
            + "{zipCode}&key={key}";

    @Value("${api.key")
    private String apiKey;

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder){
//        return builder.build();
//    }

    public TimeZone getTimeZone(int zipCode) {
//        TimeZoneResponse response = restTemplate.getForObject(timezone_url, TimeZoneResponse.class, zipCode, apiKey);
//        if (response.getResult() != null)
//            return response.getResult();
//        else {
//            return null;
//        }
        return null;
    }
}

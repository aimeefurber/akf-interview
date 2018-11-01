package zipcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class ZipCodeController {

    private RestTemplate restTemplate;
    private final GoogleGeocode googleGeocode;
    private final GoogleTimeZone googleTimeZone;
    private final GoogleElevation googleElevation;
    private final OpenWeather openWeather;

    @Autowired
    public ZipCodeController(RestTemplateBuilder builder, GoogleGeocode googleGeocode, GoogleTimeZone googleTimeZone,
                             GoogleElevation googleElevation, OpenWeather openWeather) {
        this.restTemplate = builder.build();
        this.googleGeocode = googleGeocode;
        this.googleTimeZone = googleTimeZone;
        this.googleElevation = googleElevation;
        this.openWeather = openWeather;
    }

    @Value("${google.api.key}")
    String googleApiKey;

    @Value("${weather.api.key}")
    String weatherApiKey;

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping("/zipdata")
    public String zipCode(@RequestParam(value = "zip") String zipCode, Model model) {
        GeocodeResponse geocode = googleGeocode.getGeoCode(zipCode, restTemplate, googleApiKey);
        final String lat = googleGeocode.getLat(geocode);
        final String lng = googleGeocode.getlng(geocode);
        final TimeZone timeZone = googleTimeZone.getTimeZone(lat, lng, restTemplate, googleApiKey);
        final Long elevation = googleElevation.getElevation(lat, lng, restTemplate, googleApiKey);
        final WeatherResponse weather = openWeather.getWeather(zipCode, restTemplate, weatherApiKey);

        model.addAttribute("timezone", timeZone.getTimeZoneName());
        model.addAttribute("elevation", elevation);
        model.addAttribute("city", weather.getName());
        model.addAttribute("temperature", openWeather.getTemperature(weather));

        return "zipdata";
    }
}

package zipcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
public class ZipCodeController {

    private RestTemplate restTemplate;

    @Autowired
    public ZipCodeController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Value("${google.api.key}")
    String googleApiKey;

    @Value("${weather.api.key}")
    String weatherApiKey;

    @RequestMapping("/")
    public String index(){
       return "index";
    }

    @RequestMapping("/zipdata")
    public String zipCode(@RequestParam(value = "zip") String zipCode, Model model) {
        GoogleGeocode googleGeocode = new GoogleGeocode();
        GeocodeResponse geocode = googleGeocode.getGeoCode(zipCode, restTemplate, googleApiKey);
        final String lat = geocode.getResults()[0].getGeometry().getLocation().getLat();
        final String lng = geocode.getResults()[0].getGeometry().getLocation().getLng();

        GoogleTimeZone googleTimeZone = new GoogleTimeZone();
        final Long timeStamp = System.currentTimeMillis() / 1000L;
        TimeZone timeZone = googleTimeZone.getTimeZone(lat, lng, timeStamp.toString(), restTemplate, googleApiKey);
        model.addAttribute("timezone", timeZone.getTimeZoneName());

        GoogleElevation googleElevation = new GoogleElevation();
        ElevationResponse elevation = googleElevation.getElevation(lat, lng, restTemplate, googleApiKey);
        long roundedElevation = Math.round(elevation.getResults()[0].getElevation());
        model.addAttribute("elevation", roundedElevation);

        OpenWeather openWeather = new OpenWeather();
        WeatherResponse weather = openWeather.getWeather(zipCode, restTemplate, weatherApiKey);
        model.addAttribute("city", weather.getName());
        model.addAttribute("temperature", weather.getWeatherDetails().getTemperature());

        return "zipdata";
    }
}

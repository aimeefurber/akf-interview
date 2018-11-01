package zipcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(ZipCodeController.class)
public class ZipCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoogleGeocode mockedGeoCode;
    @MockBean
    private GoogleTimeZone mockedGoogleTimeZone;
    @MockBean
    private GoogleElevation mockedElevation;
    @MockBean
    private OpenWeather mockedWeather;

    @Test
    public void zipCodeShouldReturnDetails() throws Exception {
        final String lat = "some lat";
        final String lng = "some lng";
        final String timeZoneName = "Pacific Daylight Time";
        final Long elevation = 97L;
        final String cityName = "Portland";
        final String temperature = "57.79";
        final GeocodeResponse geocodeResponse = new GeocodeResponse();
        TimeZone timeZone = new TimeZone();
        timeZone.setTimeZoneName(timeZoneName);
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setName(cityName);

        when(mockedGeoCode.getGeoCode(anyString(), any(RestTemplate.class), anyString())).thenReturn(geocodeResponse);
        when(mockedGeoCode.getLat(geocodeResponse)).thenReturn(lat);
        when(mockedGeoCode.getlng(geocodeResponse)).thenReturn(lng);
        when(mockedGoogleTimeZone.getTimeZone(lat, lng, any(RestTemplate.class), anyString())).thenReturn(timeZone);
        when(timeZone.getTimeZoneName()).thenReturn(timeZoneName);
        when(mockedElevation.getElevation(lat, lng, any(RestTemplate.class), anyString())).thenReturn(elevation);
        when(mockedWeather.getWeather(anyString(), any(RestTemplate.class), anyString())).thenReturn(weatherResponse);
        when(mockedWeather.getTemperature(weatherResponse)).thenReturn(temperature);

        this.mockMvc.perform(get("/zipdata").param("zip", "97221"))
                .andExpect(content().string(contains(timeZoneName)))
                .andExpect(content().string(contains(cityName)))
                .andExpect(content().string(contains(elevation.toString())))
                .andExpect(content().string(contains(temperature)));
    }
}

package zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipCode {
    private int zipCode;
    private String cityName;
    private float temperature;
    private String timeZone;
    private float elevation;

    public ZipCode() {

    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return "ZipCode{" +
                "zipCode=" + zipCode +
                ", cityName='" + cityName + '\'' +
                ", temperature=" + temperature +
                ", timeZone='" + timeZone + '\'' +
                ", elevation=" + elevation +
                '}';
    }
}

package zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Elevation {
    @JsonProperty("elevation")
    private String elevation;

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return "Elevation{" +
                "elevation='" + elevation + '\'' +
                '}';
    }
}

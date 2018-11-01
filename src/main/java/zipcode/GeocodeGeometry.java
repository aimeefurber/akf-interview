package zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeGeometry {
    @JsonProperty("location")
    private GeocodeLocation location;

    public GeocodeLocation getLocation() {
        return location;
    }

    public void setLocation(GeocodeLocation location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "GeocodeGeometry{" +
                "location=" + location +
                '}';
    }
}

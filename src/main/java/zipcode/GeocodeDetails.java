package zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeDetails {
    @JsonProperty("geometry")
    private GeocodeGeometry geometry;

    public GeocodeGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(GeocodeGeometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "GeocodeDetails{" +
                "geometry=" + geometry +
                '}';
    }
}

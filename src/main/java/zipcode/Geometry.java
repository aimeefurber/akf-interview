package zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    private String geometry;

    public String getGeometry() {
        return geometry;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "geometry='" + geometry + '\'' +
                '}';
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
}

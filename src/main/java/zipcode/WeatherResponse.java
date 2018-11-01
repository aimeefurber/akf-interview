package zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResponse {

    @JsonProperty("results")
    private GeocodeResults[] results;

    public GeocodeResults[] getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "GeocodeResponse{" +
                "results=" + Arrays.toString(results) +
                '}';
    }

    public void setResults(GeocodeResults[] results) {
        this.results = results;
    }
}

package zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ElevationResponse {

    @JsonProperty("results")
    private ElevationResults[] results;

    public ElevationResults[] getResults() {
        return results;
    }

    public void setResults(ElevationResults[] results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ElevationResponse{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}

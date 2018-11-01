package zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeZoneResponse {
    @JsonProperty("-property")
    private TimeZone result;

    public TimeZone getResult() {
        return result;
    }

    public void setResult(TimeZone result) {
        this.result = result;
    }

}

# Aimee Furber Interview

## Assignment

Create a service using Java Spring Boot (and any additional libraries of your choice) that takes ZIP-code as a parameter, then outputs city name, current temperature, time zone, and general elevation at the location with a user-friendly message. For example, “At the location $CITY_NAME, the temperature is $TEMPERATURE, the timezone is $TIMEZONE, and the elevation is $ELEVATION”. Include documentation with any necessary build instructions and be prepared to discuss your approach.


- Use the Open WeatherMap current weather API to retrieve the current temperature and city name. You will be required to sign up for a free API key.
- Use the Google Time Zone API to get the current timezone for a location. You will again need to register a “project” and sign up for a free API key * with Google.
- Use the Google Elevation API to retrieve elevation data for a location.

## Build Instructions
- `mvn clean package`
- `java -jar target/cayuse-interview-0.0.1-SNAPSHOT.jar`
To override default properties with other build environments:
`java -jar target/cayuse-interview-0.0.1-SNAPSHOT.jar --google.api.key=${googleAPIkey} --weather.api.key=${weatherAPIkey}`

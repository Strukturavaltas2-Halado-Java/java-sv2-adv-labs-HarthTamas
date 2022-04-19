package locations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LocationRepeatTest {

    double[][] values = {{0, 2, 1}, {81, 45, 0}, {0, -34, 1}, {-34, 45, 0}};

    @RepeatedTest(value = 4, name = "Is on equator? {currentRepetition}/{totalRepetitions}")
    void testEquator(RepetitionInfo info) {
        Location location = new Location("Budapest", values[info.getCurrentRepetition() - 1][0], values[info.getCurrentRepetition() - 1][1]);
        assertEquals(values[info.getCurrentRepetition() - 1][2] == 1, location.isOnEquator());
    }

    @ParameterizedTest(name = "Latitude {0}, Longitude = {1}, Expected = {2}")
    @MethodSource("getLocations")
    void testPrimeMeridian(int latitude, int longitude, boolean expected) {
        assertEquals(expected, new Location("valami", latitude, longitude).isOnEquator());
    }

    static Stream<Arguments> getLocations() {
        return Stream.of(
                Arguments.arguments(0, 2, true),
                Arguments.arguments(81, 45, false),
                Arguments.arguments(0, -34, true),
                Arguments.arguments(-34, 45, false)
        );
    }

    @ParameterizedTest(name = "Latitude: {0}, Longitude: {0}, Expected: {2}, ")
    @CsvFileSource(resources = "/location.csv")
    void testPrimeMeridianFromFile(int latitude, int longitude, boolean expected) {
        assertEquals(expected, new Location("valami", latitude, longitude).isOnEquator());
    }
}

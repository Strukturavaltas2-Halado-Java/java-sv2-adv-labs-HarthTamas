package locations;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LocationTest {

    LocationParser testLocationParser;

    @BeforeEach
    void init() {
        testLocationParser = new LocationParser();
    }

    @Test
    @DisplayName("Location is on Equator")
    void testIsOnEquator() {
        Location testLocation = new Location("Valami", 0, 12);
        assertTrue(testLocation.isOnEquator());
    }

    @Test
    @DisplayName("Location is NOT on Equator")
    void testIsOnEquatorFalse() {
        Location testLocation = new Location("Valami", 1, 12);
        assertFalse(testLocation.isOnEquator());
    }

    @Test
    @DisplayName("Location is on prime meridian")
    void testIsOnPrimeMeridian() {
        Location testLocation = new Location("Másvalami", 12, 0);
        assertTrue(testLocation.isOnPrimeMeridian());
    }

    @Test
    @DisplayName("Location is NOT on prime meridian")
    void testIsOnPrimeMeridianFalse() {
        Location testLocation = testLocationParser.parse("Másvalami, 12,1");
        assertFalse(testLocation.isOnPrimeMeridian());
    }

    @Test
    void testTwoDifferentLocationParser() {
        Location location = new LocationParser().parse("Budapest,47.497912,19.040235");
        Location location2 = new LocationParser().parse("Budapest,47.497912,19.040235");
        assertNotSame(location, location2);
    }

    @Test
    @DisplayName("Test if distance is correct")
    void testDistanceFrom() {
        Location location = new LocationParser().parse("Budapest,47.497912,19.040235");
        Location location2 = new LocationParser().parse("Debrecen, 47.52997,21.63916");

        assertEquals(195.2, location.distanceFrom(location2), 0.01);

        assertEquals(0, location.distanceFrom(location));
    }

    @Test
    @DisplayName("Test name, lat & lon")
    void testParseAll() {
        Location location = new LocationParser().parse("Budapest,47.497912,19.040235");

        assertAll(
                () -> assertEquals("Budapest", location.getName()),
                () -> assertEquals(47.497912, location.getLatitude()),
                () -> assertEquals(19.040235, location.getLongitude())
        );
    }

    @Test
    @DisplayName("Are these two locations different?")
    void testDifferentLocations() {
        Location location = new LocationParser().parse("Budapest,47.497912,19.040235");
        Location location2 = new LocationParser().parse("Budapest,47.497912,19.040235");

        assertAll(
                ()->assertEquals(location,location2),
                ()->assertNotSame(location,location2)
        );
    }

    @Test
    @DisplayName("Here we test the latitude validation")
    void testCreateLocationWithWrongLatitude() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                ()->new Location("Budapest",-100,100));
        assertEquals("Wrong latitude value: -100.0",iae.getMessage());
    }

    @Test
    @DisplayName("Here we test the longitude validation")
    void testCreateLocationWithWrongLongitude() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                ()-> new Location("Veszprém",50,365));
        assertEquals("Wrong longitude value: 365.0",iae.getMessage());
    }
}
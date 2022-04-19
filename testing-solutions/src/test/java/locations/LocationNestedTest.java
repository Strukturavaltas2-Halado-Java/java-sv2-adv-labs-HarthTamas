package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationNestedTest {

    Location location;

    @BeforeEach
    void initLocationNestedTest() {
        LocationParser locationParser = new LocationParser();
    }

    @Nested
    class favouritePlaceNrOne {
        @BeforeEach
        void initFavouritePlaceNrOne() {
            location = new Location("Favourite Place Nr. 1", 0, 0);
        }

        @Test
        void IsLocationOnEquator() {
            assertTrue(location.isOnEquator());
        }

        @Test
        void IsLocationOnPrimeMeridian() {
            assertTrue(location.isOnPrimeMeridian());
        }
    }

    @Nested
    class favouritePlaceNrTwo {
        @BeforeEach
        void initFavouritePlaceNrTwo() {
            location = new Location("Favourite Place Nr. 2.", 47.497912, 19.040235);
        }

        @Test
        void IsLocationOnEquator() {
            assertFalse(location.isOnEquator());
        }

        @Test
        void IsLocationOnPrimeMeridian() {
            assertFalse(location.isOnPrimeMeridian());
        }
    }
}
package locations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    @Test
    void test() {
        assertTrue(new LocationsService(LocationRepository).calculateDistance("Paris", "Kiev"));
    }

}
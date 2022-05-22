package locations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {

    @Test
    void testGetLocations() {
        LocationService service = new LocationService();
        assertThat(service.getLocations())
                .extracting(Location::getName, Location::getLat)
                .contains(tuple("Győr",22.0),tuple("Zanzibar",0.3));
    }
}
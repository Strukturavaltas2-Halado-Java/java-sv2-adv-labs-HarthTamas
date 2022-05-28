package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LocationsControllerIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void testGetLocation() {
        List<LocationDto> locations = locationsController.getLocations(Optional.of("zan"));
        assertThat(locations).extracting(LocationDto::getName).contains("Zanzibar");
    }

    @Test
    void testFindLocationById() {
        LocationDto expected = locationsController.findLocationById(3);
        assertThat(expected).extracting(LocationDto::getName).isEqualTo("Budapest");
    }

    @Test
    void testFindLocationByIdThrowException() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> locationsController.findLocationById(5));
        assertEquals("Location not found: 5", iae.getMessage());
    }

    @Test
    void testGetLocationsByAllParams() {
        List<LocationDto> locations = locationsController.getLocationsByAllParams(Optional.of("bal"),
                Optional.of(40.0),Optional.empty(),Optional.of(60.0),Optional.empty());
        assertThat(locations).extracting(LocationDto::getName).containsExactly("Baltimore");

        locations = locationsController.getLocationsByAllParams(Optional.of("b"),
                Optional.of(0.0),Optional.of(10.0),Optional.of(30.0),Optional.of(11.5));
        assertThat(locations).extracting(LocationDto::getName).containsExactly("Budapest");
    }

}

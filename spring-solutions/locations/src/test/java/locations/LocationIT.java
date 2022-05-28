package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LocationIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void testGetLocation() {
        List<LocationDto> locations = locationsController.getLocations();
        assertThat(locations).extracting(LocationDto::getName).contains("Zanzibar")
                .contains("Győr");
    }
}

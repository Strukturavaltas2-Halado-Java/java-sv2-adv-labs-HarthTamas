package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {


    @Mock
    LocationService service;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void testGetLocations() {
        when(service.getLocations()).thenReturn(
                List.of(new Location(0L,"Zanzibar",0.3,1.2), new Location(0L,"Győr",22.0,11.97)));
       String locations = locationsController.getLocations();
       assertThat(locations).contains("Zanzibar")
               .contains("Győr");

    }
}
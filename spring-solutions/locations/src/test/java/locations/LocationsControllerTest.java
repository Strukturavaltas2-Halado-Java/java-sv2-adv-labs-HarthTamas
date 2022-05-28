package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {


    @Mock
    LocationService service;

    @InjectMocks
    LocationsController locationsController;

    // Get operations
//    @Test
//    void testGetLocations() {
//        when(service.getLocations()).thenReturn(new ArrayList<>(
//                List.of(new LocationDto(0L,"Zanzibar",0.3,1.2), new LocationDto(0L,"Győr",22.0,11.97))));
//       List<LocationDto> locations = locationsController.getLocations();
//       assertThat(locations).extracting(LocationDto::getName).contains("Zanzibar")
//               .contains("Győr");
//    }



// Get operations + parameters
    @Test
    void testGetLocations() {
        when(service.getLocations(Optional.of("zan"))).thenReturn(new ArrayList<>(
                List.of(new LocationDto(1L, "Zanzibar", 0.3, 1.2))));
        List<LocationDto> locations = locationsController.getLocations(Optional.of("zan"));
        assertThat(locations).extracting(LocationDto::getName).containsExactly("Zanzibar");
    }

    @Test
    void testFindLocationById() {
        when(service.findLocationById(anyLong()))
                .thenReturn(new LocationDto(1L, "Zanzibar", 0.3, 1.2));
        LocationDto location = locationsController.findLocationById(1);
        assertThat(location.getName()).isEqualTo("Zanzibar");
    }

    @Test
    void testFindLocationByIdThrowException() {
        when(service.findLocationById(5)).thenThrow(new IllegalArgumentException("Location not found: 5"));
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> locationsController.findLocationById(5));
        assertEquals("Location not found: 5", result.getMessage());
    }

}
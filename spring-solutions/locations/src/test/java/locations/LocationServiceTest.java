package locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocationServiceTest {

    @Autowired
    LocationService service;

    @Autowired
    ModelMapper modelMapper;

    @Test
    void testGetLocations() {
        assertThat(service.getLocations(Optional.of("zan")))
                .extracting(LocationDto::getName, LocationDto::getLat)
                .contains(tuple("Zanzibar", 0.3));
    }

    @Test
    void testFindLocationById() {
        assertThat(service.findLocationById(1).getName()).isEqualTo("Zanzibar");
    }

    @Test
    void testFindLocationByIdThrowException() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> service.findLocationById(5));
        assertEquals("Location not found: 5", iae.getMessage());
    }


    @Test
    void testGetLocationWithParams() {

        assertThat(service.getLocationsByAllParams(Optional.of("bal"),
                Optional.of(40.0), Optional.empty(), Optional.of(60.0), Optional.empty()))
                .extracting(LocationDto::getName, LocationDto::getLat)
                .contains(tuple("Baltimore", 43.1));

        assertThat(service.getLocationsByAllParams(Optional.of("bu"),
                Optional.of(0.0), Optional.of(10.0), Optional.of(30.0), Optional.of(11.5)))
                .extracting(LocationDto::getName, LocationDto::getLat)
                .contains(tuple("Budapest", 22.3));
    }


}
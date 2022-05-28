package locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class LocationServiceTest {

    private ModelMapper modelMapper;

    @Test
    void testGetLocations() {
        modelMapper = new ModelMapper();
        LocationService service = new LocationService(modelMapper);
        assertThat(service.getLocations())
                .extracting(LocationDto::getName, LocationDto::getLat)
                .contains(tuple("Győr",22.0),tuple("Zanzibar",0.3));
    }
}
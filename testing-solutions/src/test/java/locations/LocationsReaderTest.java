package locations;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SoftAssertionsExtension.class)
public class LocationsReaderTest {


    @Test
    void testReadLocations() {
        LocationsReader locationsReader = new LocationsReader();
        List<Location> locations = locationsReader.readLocations("src/test/resources/location_2.csv");
        assertThat(locations)
                .hasSize(5)
                .extracting(Location::getName)
                .contains("Kiev")
                .doesNotContain("Malibu")
                .containsOnly("Budapest", "Oslo", "Milano", "Kiev", "Honolulu");


    }

    @Test
    void testFilterLocationsBeyondArcticCircle() {
        LocationsReader locationsReader = new LocationsReader();
        List<Location> locations = locationsReader.readLocations("src/test/resources/location_2.csv");
        List<Location> locationsBeyondArcticCircle = locationsReader.filterLocationsBeyondArcticCircle(locations);

        assertThat(locationsBeyondArcticCircle)
                .filteredOn(location -> location.getLatitude() == location.getLongitude())
                .extracting(Location::getName, Location::getLatitude)
                .containsOnly(tuple("Kiev", 72.497912));
    }

    @Test
    void newOne(SoftAssertions softly) {
        Location location = new Location("Abc",1,1);

//        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(location.getName()).startsWith("b");
        softly.assertThat(location.getName()).endsWith("c");
//        softly.assertAll();
    }
}



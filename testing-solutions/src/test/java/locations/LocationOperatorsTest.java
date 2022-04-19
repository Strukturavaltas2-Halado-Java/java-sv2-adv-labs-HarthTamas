package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@LocationOperationsFeatureTest
class LocationOperatorsTest {

    List<Location> locations = new ArrayList<>();


    @BeforeEach
    void initCreateLocationList() {
        locations.add(new Location("Valami", 47, 10));
        locations.add(new Location("Másvalami", -14, 20));
        locations.add(new Location("Harmadikvalami", 2, 2));
    }

    @Test
    @DisplayName("Test if location is on north")
    void testNorthLocations() {
        List<String> locationNames = new LocationOperators().filterOnNorth(locations).stream()
                .map(Location::getName)
                .collect(Collectors.toList());

        assertEquals(List.of("Valami", "Harmadikvalami"), locationNames);
    }
}
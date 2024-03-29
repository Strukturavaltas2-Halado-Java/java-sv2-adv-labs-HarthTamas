package locations;

import java.util.List;
import java.util.stream.Collectors;

public class LocationOperators {

    public List<Location> filterOnNorth(List<Location> locations) {
        return locations.stream()
                .filter(location -> location.getLatitude()>0)
                .collect(Collectors.toList());
    }
}

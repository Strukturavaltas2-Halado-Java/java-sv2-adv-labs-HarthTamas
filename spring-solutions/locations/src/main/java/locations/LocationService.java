package locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    List<Location> locations = new ArrayList<>();

    public List<Location> getLocations() {
        locations.add(new Location(0L,"Zanzibar",0.3,1.2));
        locations.add(new Location(0L,"Baltimore",43.1,-11.8));
        locations.add(new Location(0L,"Budapest",22.3,11.2));
        locations.add(new Location(0L,"Győr",22.0,11.97));
        return locations;
    }
}

package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationsController {

    List<Location> locations = new ArrayList<>();

    @GetMapping("/locations")
    public String getLocations() {
        locations.add(new Location(0L,"Zanzibar",0.3,1.2));
        locations.add(new Location(0L,"Baltimore",43.1,-11.8));
        return locations.toString();
    }
}

package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationsController {

    private LocationService locationService;

    public LocationsController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public String getLocations() {
        StringBuilder sb = new StringBuilder();
        for (Location actual: locationService.getLocations()) {
            sb.append(actual);
        }
        return sb.toString();
    }
}

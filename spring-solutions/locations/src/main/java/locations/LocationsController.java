package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class LocationsController {

    private LocationService locationService;

    public LocationsController(LocationService locationService) {
        this.locationService = locationService;
    }


    @GetMapping("/locations")
    public List<LocationDto> getLocations() {
        return locationService.getLocations();
    }
}

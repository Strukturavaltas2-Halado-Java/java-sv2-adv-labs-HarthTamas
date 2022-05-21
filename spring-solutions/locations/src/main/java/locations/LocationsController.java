package locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        List<Location> locations = locationService.getLocations();
        for (Location actual : locations) {
            sb.append(actual);
        }
        return sb.toString();
    }
}

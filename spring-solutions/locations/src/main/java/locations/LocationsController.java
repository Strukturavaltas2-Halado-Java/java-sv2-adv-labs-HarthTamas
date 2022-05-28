package locations;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    private LocationService locationService;

    public LocationsController(LocationService locationService) {
        this.locationService = locationService;
    }


    @GetMapping("/all")
    public List<LocationDto> getLocations(@RequestParam Optional<String> prefix) {
        return locationService.getLocations(prefix);
    }

    @GetMapping("/{id}")
    public LocationDto findLocationById(@PathVariable("id") long id) {
        return locationService.findLocationById(id);
    }

    @GetMapping
    public List<LocationDto> getLocationsByAllParams(@RequestParam Optional<String> prefix,
                                                     @RequestParam Optional<Double> minLat, @RequestParam Optional<Double> minLon,
                                                     @RequestParam Optional<Double> maxLat, @RequestParam Optional<Double> maxLon){
        return locationService.getLocationsByAllParams(prefix,minLat,minLon,maxLat,maxLon);
    }

    @PostMapping
    public LocationDto createLocationDto(@RequestBody CreateLocationCommand command) {
        return locationService.createLocation(command);
    }

}

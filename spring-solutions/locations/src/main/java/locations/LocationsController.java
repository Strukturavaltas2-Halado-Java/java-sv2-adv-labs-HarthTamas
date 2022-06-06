package locations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
@Tag(name="Operation on locations")
public class LocationsController {

    private LocationService locationService;

    public LocationsController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/all")
    @Operation(summary="Gets all the locations")
    @ApiResponse(responseCode = "",description = "")
    public List<LocationDto> getLocations(@RequestParam Optional<String> prefix) {
        return locationService.getLocations(prefix);
    }

    @GetMapping("/{id}")
    @Operation(summary="Gets all the locations")
    public LocationDto findLocationById(@PathVariable("id") long id) {
        return locationService.findLocationById(id);
    }

    @GetMapping
    @Operation(summary="Gets the locations with parameters")
    public List<LocationDto> getLocationsByAllParams(@RequestParam Optional<String> prefix,
                                                     @RequestParam Optional<Double> minLat, @RequestParam Optional<Double> minLon,
                                                     @RequestParam Optional<Double> maxLat, @RequestParam Optional<Double> maxLon) {
        return locationService.getLocationsByAllParams(prefix, minLat, minLon, maxLat, maxLon);
    }

    @PostMapping
    @Operation(summary="Create a location")
    @ApiResponse(responseCode = "201",description = "Location has been created")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto createLocationDto(@RequestBody CreateLocationCommand command) {
            return locationService.createLocation(command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Delete location by id")
    @ApiResponse(responseCode = "204",description = "No content")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") long id) {
        locationService.deleteLocation(id);
    }

    @PutMapping("/{id}")
    @Operation(summary="Update location by id")
    public LocationDto updateLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command) {
        return locationService.updateLocation(id, command);
    }
}

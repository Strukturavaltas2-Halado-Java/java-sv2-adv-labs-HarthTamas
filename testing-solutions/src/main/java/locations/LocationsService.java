package locations;

import java.util.Optional;

public class LocationsService {

    private LocationRepository locationRepository;

    public LocationsService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Double> calculateDistance(String name1, String name2) {
        Optional<Location> optionalLocation1 = locationRepository.findByName(name1);
        Optional<Location> optionalLocation2 = locationRepository.findByName(name2);
        if (optionalLocation1.isEmpty() || optionalLocation2.isEmpty()) {
            return Optional.empty();
        } else {
            Location firstLocation = optionalLocation1.get();
            Location secondLocation = optionalLocation2.get();
            return Optional.of(firstLocation.distanceFrom(secondLocation));
        }
    }
}
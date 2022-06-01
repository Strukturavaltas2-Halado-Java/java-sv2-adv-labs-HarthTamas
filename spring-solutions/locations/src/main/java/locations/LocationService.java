package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();

    public LocationService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(idGenerator.incrementAndGet(), "Zanzibar", 0.3, 1.2),
            new Location(idGenerator.incrementAndGet(), "Baltimore", 43.1, -11.8),
            new Location(idGenerator.incrementAndGet(), "Budapest", 22.3, 11.2),
            new Location(idGenerator.incrementAndGet(), "Győr", 22.0, 11.97)
    )));


    public List<LocationDto> getLocations(Optional<String> prefix) {
        Type targetListType = new TypeToken<List<LocationDto>>() {}.getType();
        List<Location> filtered = locations.stream()
                .filter(location -> prefix.isEmpty() || location.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());
        List<LocationDto> locationDtoList = modelMapper.map(filtered, targetListType);
        return locationDtoList;
    }

    public LocationDto findLocationById(long id) {
        return modelMapper.map(locations.stream()
                .filter(location -> location.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException(id)), LocationDto.class);
    }

    public List<LocationDto> getLocationsByAllParams(Optional<String> prefix, Optional<Double> minLat, Optional<Double> minLon,
                                                     Optional<Double> maxLat, Optional<Double> maxLon) {
        Type targetListType = new TypeToken<List<LocationDto>>() {}.getType();
        List<Location> filtered = locations.stream()
                .filter(location -> prefix.isEmpty() || location.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .filter(location -> minLat.isEmpty() || location.getLat() >= minLat.get())
                .filter(location -> minLon.isEmpty() || location.getLon() >= minLon.get())
                .filter(location -> maxLat.isEmpty() || location.getLat() <= maxLat.get())
                .filter(location -> maxLon.isEmpty() || location.getLon() <= maxLon.get())
                .collect(Collectors.toList());
        List<LocationDto> locationDtoList = modelMapper.map(filtered, targetListType);
        return locationDtoList;
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(idGenerator.incrementAndGet(), command.getName(),command.getLat(), command.getLon());
        locations.add(location);
        return modelMapper.map(location,LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location location = locations.stream()
                .filter(l -> l.getId()==id)
                .findFirst().orElseThrow(()->new LocationNotFoundException(id));
        locations.remove(location);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = locations.stream()
                .filter(l -> l.getId()==id)
                .findFirst().orElseThrow(()->new LocationNotFoundException(+id));
        location.setName(command.getName());
        location.setLat(command.getLat());
        location.setLon(command.getLon());
        return modelMapper.map(location,LocationDto.class);
    }
}

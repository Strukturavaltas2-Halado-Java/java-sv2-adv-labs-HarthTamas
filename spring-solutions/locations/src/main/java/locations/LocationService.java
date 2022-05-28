package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    private ModelMapper modelMapper;

    public LocationService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    List<Location> locations = new ArrayList<>();

    public List<LocationDto> getLocations() {
        locations.add(new Location(0L,"Zanzibar",0.3,1.2));
        locations.add(new Location(0L,"Baltimore",43.1,-11.8));
        locations.add(new Location(0L,"Budapest",22.3,11.2));
        locations.add(new Location(0L,"Győr",22.0,11.97));

        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
        List<LocationDto> locationDtoList = modelMapper.map(locations,targetListType);
        return locationDtoList;
    }
}

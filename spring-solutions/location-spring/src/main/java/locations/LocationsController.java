package locations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LocationsController {

    List<Location> locations = new ArrayList<>();

    @GetMapping("/")
    @ResponseBody
    public String getLocations() {
        locations.add(new Location(0L,"Siklós",23.01,0.34));
        locations.add(new Location(1L,"Sásd",22.01,1.34));
        return locations.toString();
    }

}

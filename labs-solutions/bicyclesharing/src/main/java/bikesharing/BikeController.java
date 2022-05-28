package bikesharing;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class BikeController {

    private BikeService service;

    @GetMapping("/rentals")
    public Set<BikeRental> getAllRentals() {
        return service.getAllRentals();
    }

    @GetMapping("/users")
    public Set<String> getAllUserIds() {
        return service.getAllUserIds();
    }
}

package bikesharing.controll;

import bikesharing.dtos.CreateBikeRentalCommand;
import bikesharing.dtos.BikeRentalDTO;
import bikesharing.service.BikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bikerental")
public class BikeController {

    private BikeService service;

    @GetMapping("/all")
    public Set<BikeRentalDTO> getAllRentals() {
        return service.getAllRentals();
    }

    @GetMapping("/rentals")
//    public Set<BikeRental> getAllRentalsAfterStartTime(@RequestParam Optional<LocalDateTime> startTime) {
//        return service.getRentalsAfterStartTime(startTime);
    public Set<BikeRentalDTO> getRentalsAfterStartTime(@RequestParam Optional<String> startTime) {
        return service.getRentalsAfterStartTime(Optional.of(LocalDateTime.parse(startTime.get())));
    }

    @GetMapping("/users")
    public Set<String> getAllUserIds() {
        return service.getAllUserIds();
    }

    @GetMapping("/{id}")
    public BikeRentalDTO getRentalById(@PathVariable long id) {
        return service.getRentalById(id);
    }

    @PostMapping("/rentals")
    public BikeRentalDTO createRental(@RequestBody CreateBikeRentalCommand command) {
        return service.createBikeRental(command);
    }

}

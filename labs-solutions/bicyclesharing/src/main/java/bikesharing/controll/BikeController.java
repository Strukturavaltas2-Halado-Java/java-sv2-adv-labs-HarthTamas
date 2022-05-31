package bikesharing.controll;

import bikesharing.dtos.CreateBikeRentalCommand;
import bikesharing.dtos.BikeRentalDTO;
import bikesharing.service.BikeService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rentals")
public class BikeController {

    private BikeService service;

    @GetMapping
    public Set<BikeRentalDTO> getAllRentalsAfterStartTime(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> startTime) {
        return service.getAllRental(startTime);
    }

    @GetMapping("/users")
    public Set<String> getAllUserIds() {
        return service.getAllUserIds();
    }

    @GetMapping("/{id}")
    public BikeRentalDTO getRentalById(@PathVariable long id) {
        return service.getRentalById(id);
    }

    @PostMapping
    public BikeRentalDTO createRental(@RequestBody CreateBikeRentalCommand command) {
        return service.createBikeRental(command);
    }

}

package usedcars;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/cars")
public class CarController {

    private CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping
    public List<CarDTO> getCars(@RequestParam Optional<String> brand, @RequestParam Optional<Integer> age, @RequestParam Optional<String> sort) {
        return service.getCars(brand, age, sort);
    }

    @GetMapping("/brands")
    public Set<String> getAllBrandsInAlphabeticalOrder() {
        return service.getAllBrandsInAlphabeticalOrder();
    }

    @GetMapping("{id}")
    public CarDTO getCarById(@PathVariable long id) {
        return service.getCarById(id);
    }

    @DeleteMapping("{id}")
    public void deleteCarById(@PathVariable long id) {
        service.deleteCarById(id);
    }

    @PostMapping
    public CarDTO createCar(@RequestBody CreateCarCommand command) {
        return service.createCar(command);
    }

    @PostMapping("/{id}/kilometer-states")
    public CarDTO createKilometerStatesById(@PathVariable long id, @RequestBody CreateKilometerStatesCommand command) {
        return service.createKilometerStatesById(id, command);
    }
}

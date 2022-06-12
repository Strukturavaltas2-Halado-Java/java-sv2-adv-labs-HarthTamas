package usedcars.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import usedcars.service.CarSellingService;
import usedcars.dtos.CarDTO;
import usedcars.dtos.CreateCarCommand;
import usedcars.dtos.CreateKilometerStatesCommand;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/cars")
public class CarController {

    private CarSellingService service;

    public CarController(CarSellingService service) {
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarById(@PathVariable long id) {
        service.deleteCarById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@RequestBody CreateCarCommand command) {
        return service.createCar(command);
    }

    @PostMapping("/{id}/kilometer-states")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createKilometerStatesById(@PathVariable long id, @RequestBody CreateKilometerStatesCommand command) {
        return service.createKilometerStatesById(id, command);
    }
}

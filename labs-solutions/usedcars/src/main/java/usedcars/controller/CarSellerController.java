package usedcars.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import usedcars.dtos.*;
import usedcars.model.CarSeller;
import usedcars.service.CarSellingService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/car-sellers")
public class CarSellerController {

    private CarSellingService service;


    @PostMapping
    public CarSellerDto createCarSeller(@RequestBody CreateCarSellerCommand createCarSellerCommand) {
        return service.createCarSeller(createCarSellerCommand);
    }

    @GetMapping
    public List<CarSellerDto> getAllCarSellers() {
        return service.getAllCarSellers();
    }

    @GetMapping("{id}")
    public CarSellerDto getCarSellerById(@PathVariable("id") Long id) {
        return service.getCarSellerById(id);
    }


    @PostMapping("/{id}/cars")
    public CarSellerDto addCarToSeller(@PathVariable("id") Long id, @RequestBody CreateCarCommand command) {
        return service.addCarToSeller(id, command);
    }


    @GetMapping("{sellerId}/cars")
    public List<CarDTO> getCarsFromCarSeller(@PathVariable("sellerId") long sellerId) {
        return service.getCarsFromCarSeller(sellerId);
    }

    @DeleteMapping("{id}")
    public void deleteCarSellerById(@PathVariable("id") long id) {
        service.deleteCarSellerById(id);
    }

    @GetMapping("{id}/brands")
    public List<String> getBrandsFromSellerById(@PathVariable("id") long id) {
        return service.getBrandsFromSellerById(id);
    }



}

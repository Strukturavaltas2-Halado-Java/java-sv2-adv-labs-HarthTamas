package usedcars.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import usedcars.dtos.*;
import usedcars.exceptions.CarSellerNotFoundException;
import usedcars.model.CarSeller;
import usedcars.model.KilometerState;
import usedcars.exceptions.CarNotFoundException;
import usedcars.exceptions.KilometerStateException;
import usedcars.model.Car;
import usedcars.repository.CarSellerRepository;
import usedcars.repository.CarsRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class CarSellingService {


    private ModelMapper modelMapper;
    private CarSellerRepository carSellerRepository;
    private CarsRepository carsRepository;


    public CarSellingService(ModelMapper modelMapper, CarSellerRepository carSellerRepository, CarsRepository carsRepository) {
        this.modelMapper = modelMapper;
        this.carSellerRepository = carSellerRepository;
        this.carsRepository = carsRepository;
    }

    public List<CarDTO> getCars(Optional<String> brand, Optional<Integer> age, Optional<String> sort) {
        if (sort.isEmpty()) {
            sort = Optional.of("empty");
        }
        switch (sort.get()) {
            case "asc":
                return carsRepository.findByOptionalParametersAsc(brand, age).stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();
            case "desc":
                return carsRepository.findByOptionalParametersDesc(brand, age).stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();
            default:
                return carsRepository.findByOptionalParameters(brand, age).stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();
        }
    }

    public CarDTO createCar(CreateCarCommand command) {
        Car car = new Car(command.getBrand(), command.getType(), command.getAge(), command.getCarCondition());
        car.addKilometerState(new KilometerState(command.getKmState(), LocalDate.now()));
        carsRepository.save(car);
        return modelMapper.map(car, CarDTO.class);
    }

    public Set<String> getAllBrandsInAlphabeticalOrder() {
        return carsRepository.getBrandsInAlphabeticalOrder();
    }

    public CarDTO getCarById(long id) {
        return modelMapper.map(carsRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id)), CarDTO.class);
    }

    public void deleteCarById(long id) {
        if (carsRepository.existsById(id)) {
            carsRepository.deleteById(id);
        } else throw new CarNotFoundException(id);
    }

    public CarDTO createKilometerStatesById(long id, CreateKilometerStatesCommand command) {
        Car actual = carsRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        if (command.getValue() < actual.getLastKilometerState()) {
            throw new KilometerStateException(id);
        }
        actual.addKilometerState(new KilometerState(command.getValue(), LocalDate.now()));
        return modelMapper.map(actual, CarDTO.class);
    }

    public CarSellerDto createCarSeller(CreateCarSellerCommand command) {
        CarSeller carSeller = new CarSeller(command.getSellerName());
        carSellerRepository.save(carSeller);
        return modelMapper.map(carSeller, CarSellerDto.class);
    }

    public List<CarSellerDto> getAllCarSellers() {
        return carSellerRepository.findAll().stream()
                .map(carSeller -> modelMapper.map(carSeller, CarSellerDto.class)).toList();
    }

    public CarSellerDto addCarToSeller(Long id, CreateCarCommand command) {
        CarSeller carSeller = carSellerRepository.findById(id).orElseThrow(() -> new CarSellerNotFoundException(id));
        Car car = new Car(command.getBrand(), command.getType(), command.getAge(), command.getCarCondition());
        car.addKilometerState(new KilometerState(command.getKmState(), LocalDate.now()));
        carsRepository.save(car);
        carSeller.addCar(car);
        return modelMapper.map(carSeller, CarSellerDto.class);
    }


    public CarSellerDto getCarSellerById(long id) {
        return modelMapper.map(carSellerRepository.findById(id).orElseThrow(() -> new CarSellerNotFoundException(id)), CarSellerDto.class);
    }

    public List<CarDTO> getCarsFromCarSeller(long sellerId) {
        List<Car> cars = carsRepository.findCarsByCarSeller_Id(sellerId);
        return cars.stream().map(car -> modelMapper.map(car,CarDTO.class)).toList();
    }

    public void deleteCarSellerById(long id) {
        if (carSellerRepository.existsById(id)) {
            List<Car> cars = carsRepository.findCarsByCarSeller_Id(id);
            carsRepository.deleteAll(cars);
            carSellerRepository.deleteById(id);
        } else {
            throw new CarSellerNotFoundException(id);
        }
    }

    public List<String> getBrandsFromSellerById(long id) {
        return carsRepository.getBrandsInAlphabeticalOrderBySellerId(id);
    }
}

package usedcars;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> cars = new ArrayList<>();

    private ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();


    public CarService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<CarDTO> getCars(Optional<String> brand, Optional<Integer> age, Optional<String> sort) {
        List<Car> result = cars.stream()
                .filter(car -> brand.isEmpty() || car.getBrand().toLowerCase().equals(brand.get().toLowerCase()))
                .filter(car -> age.isEmpty() || car.getAge() <= age.get())
                .collect(Collectors.toList());

        if (sort.isEmpty() ||sort.get().equals("asc")) {
            return ascSortedCars(result);
        }
        if (sort.isEmpty() ||sort.get().equals("desc")) {
            return descSortedCars(result);
        }
        return result.stream()
                .map(car -> modelMapper.map(car,CarDTO.class))
                .collect(Collectors.toList());
    }

    public CarDTO createCar(CreateCarCommand command) {
        Car car = new Car(idGenerator.incrementAndGet(), command.getBrand(), command.getType(), command.getAge(), command.getCondition());
        car.addKilometerState(new KilometerState(command.getKmState(), LocalDate.now()));
        cars.add(car);
        return modelMapper.map(car, CarDTO.class);
    }

    public Set<String> getAllBrandsInAlphabeticalOrder() {
        return cars.stream()
                .map(Car::getBrand)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public CarDTO getCarById(long id) {
        Car actual = findCarById(id);
        return modelMapper.map(actual, CarDTO.class);
    }

    public void deleteCarById(long id) {
        cars.remove(findCarById(id));
    }

    public CarDTO createKilometerStatesById(long id, CreateKilometerStatesCommand command) {
        Car actual = findCarById(id);
        if (command.getValue()<actual.getLastKilometerState()) {
            throw new KilometerStateException(id);
        }
        actual.addKilometerState(new KilometerState(command.getValue(), LocalDate.now()));
        return modelMapper.map(actual, CarDTO.class);
    }

    private Car findCarById(long id) {
        return cars.stream()
                .filter(car -> car.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    private List<CarDTO> descSortedCars(List<Car> result) {
        return result.stream()
                .sorted(Comparator.comparing(Car::getLastKilometerState).reversed())
                .map(car -> modelMapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

    private List<CarDTO> ascSortedCars(List<Car> result) {
        return result.stream()
                .sorted(Comparator.comparing(Car::getLastKilometerState))
                .map(car -> modelMapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

}

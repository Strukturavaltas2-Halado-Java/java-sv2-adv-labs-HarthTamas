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

    public List<CarDTO> getAllCars(Optional<String> brand, Optional<Integer> age, Optional<String> order) {
        List<CarDTO>result = cars.stream()
                .filter(car -> brand.isEmpty() || car.getBrand().equals(brand.get()))
                .filter(car -> age.isEmpty() || car.getAge() == age.get())
                .sorted(new Comparator<Car>() {
                    @Override
                    public int compare(Car o1, Car o2) {
                        return o2.getStates().get(o2.getStates().size()-1).getDateOfReading()
                                .compareTo(o1.getStates().get(o1.getStates().size()-1).getDateOfReading());
                    }
                })
                .map(car -> modelMapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
        return result;
    }

    public CarDTO createCar(CreateCarCommand command) {
        Car car = modelMapper.map(command, Car.class);
        car.setId(idGenerator.incrementAndGet());
        cars.add(car);
        return modelMapper.map(car, CarDTO.class);
    }

    public Set<String> getAllBrandsInAlphabeticalOrder() {
        return cars.stream()
                .sorted(Comparator.comparing(Car::getBrand))
                .map(car -> car.getBrand())
                .collect(Collectors.toSet());
    }

    public CarDTO getCarById(long id) {
        Car actual = findCarById(id);
        return modelMapper.map(actual, CarDTO.class);
    }

    public void deleteCarById(long id) {
        cars.remove(findCarById(id));
    }

    public CarDTO createKilometerStates(long id, CreateKilometerStatesCommand command) {
        Car actual = findCarById(id);
        actual.getStates().add(new KilometerState(command.getValue(), LocalDate.now()));
        return modelMapper.map(actual,CarDTO.class);
    }

    private Car findCarById(long id) {
       return  cars.stream()
               .filter(car -> car.getId() == id)
               .findFirst()
               .orElseThrow(() -> new CarNotFoundException("Car  not found " + id));
    }
}

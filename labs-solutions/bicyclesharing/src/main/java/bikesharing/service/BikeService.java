package bikesharing.service;

import bikesharing.model.BikeRental;
import bikesharing.dtos.BikeRentalDTO;
import bikesharing.dtos.CreateBikeRentalCommand;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class BikeService {

    private AtomicLong idGenerator = new AtomicLong();
    private Set<BikeRental> bikeRentals = new HashSet<>();

    private ModelMapper modelMapper;

    public BikeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Set<BikeRental> readFromFile() {
        try (Scanner scanner = new Scanner(Path.of("src/main/resources/bikes.csv"))) {
            while (scanner.hasNextLine()) {
                bikeRentals.add(processLine(scanner.nextLine()));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file!");
        }
        return bikeRentals;
    }

    private BikeRental processLine(String nextLine) {
        String[] temp = nextLine.split(";");
        return new BikeRental(idGenerator.incrementAndGet(), temp[0], temp[1], LocalDateTime.parse(temp[2]), Double.parseDouble(temp[3]));
    }

    public Set<BikeRentalDTO> getAllRentals() {
        if (bikeRentals.isEmpty()) {
            readFromFile();
        }
        return bikeRentals.stream()
                .map(r -> modelMapper.map(r, BikeRentalDTO.class))
                .collect(Collectors.toSet());
    }

    public Set<String> getAllUserIds() {
        if (bikeRentals.isEmpty()) {
            readFromFile();
        }
        return bikeRentals.stream()
                .map(BikeRental::getUserId)
                .collect(Collectors.toSet());
    }

    public BikeRentalDTO getRentalById(long id) {
        BikeRental result = bikeRentals.stream().filter(rental -> rental.getId() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Bike not found " + id));
        return modelMapper.map(result, BikeRentalDTO.class);
    }

    public Set<BikeRentalDTO> getRentalsAfterStartTime(Optional<LocalDateTime> startTime) {
        if (bikeRentals.isEmpty()) {
            readFromFile();
        }
        return bikeRentals.stream()
                .filter(rental -> startTime.isEmpty() || rental.getDeliveryTime().isAfter(startTime.get()))
                .map(rental -> modelMapper.map(rental, BikeRentalDTO.class))
                .collect(Collectors.toSet());
    }

    public BikeRentalDTO createBikeRental(CreateBikeRentalCommand command) {
        if (bikeRentals.isEmpty()) {
            readFromFile();
        }
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);

//        BikeRental bikeRental = new BikeRental(idGenerator.incrementAndGet(), command.getBikeId(), command.getUserId(), command.getDeliveryTime(), command.getDistance());
        BikeRental bikeRental = modelMapper.map(command, BikeRental.class);
        bikeRental.setId(idGenerator.incrementAndGet());
        bikeRentals.add(bikeRental);
        return modelMapper.map(bikeRental, BikeRentalDTO.class);
    }
}

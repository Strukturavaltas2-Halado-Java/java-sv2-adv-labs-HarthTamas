package bikesharing;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BikeService {

    private Set<BikeRental> bikes = new HashSet<>();

    public Set<BikeRental> readFromFile() {
        try (Scanner scanner = new Scanner(Path.of("src/main/resources/bikes.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] temp = line.split(";");
                bikes.add(new BikeRental(temp[0], temp[1], LocalDateTime.parse(temp[2]), Double.parseDouble(temp[3])));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file!");
        }
        return bikes;
    }

    public Set<BikeRental> getAllRentals() {
        return bikes.isEmpty() ? readFromFile() : bikes;
    }

    public Set<String> getAllUserIds() {
        if (bikes.isEmpty()) {
            readFromFile();
        }
        return bikes.stream()
                .map(BikeRental::getUserId)
                .collect(Collectors.toSet());
    }
}

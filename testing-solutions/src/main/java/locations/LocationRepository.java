package locations;

import java.util.Optional;

public interface LocationRepository {

    Optional<Location> findByName(String name);

    Optional<Double> findLatitudeByName(String name);

}

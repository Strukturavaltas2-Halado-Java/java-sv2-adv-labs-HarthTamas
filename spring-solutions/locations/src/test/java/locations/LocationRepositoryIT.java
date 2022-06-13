package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class LocationRepositoryIT {

    @Autowired
    LocationsRepository repository;

    @Test
    void testSaveAndFind() {
        Location location = new Location("Pécs",0.2,0.5);
        Location location2 = new Location("Bécs",1.2,10.5);
        repository.save(location);
        repository.save(location2);
        List<Location> found = repository.findAll();
        assertThat(found).extracting(Location::getName).containsExactly("Pécs","Bécs");
    }

    @Test
    void testSaveAndFindByParams() {
        Location location = new Location("Pécs",0.2,0.5);
        Location location2 = new Location("Bécs",1.2,10.5);
        repository.save(location);
        repository.save(location2);
        List<Location> found = repository.findAll();
        assertThat(found).extracting(Location::getName).containsExactly("Pécs","Bécs");
        found = repository.findByOptionalParameters(Optional.of("B"), Optional.of(0.0), Optional.of(0.), Optional.of(20.0), Optional.of(20.0));
        assertThat(found).extracting(Location::getName).containsExactly("Bécs");
    }
}

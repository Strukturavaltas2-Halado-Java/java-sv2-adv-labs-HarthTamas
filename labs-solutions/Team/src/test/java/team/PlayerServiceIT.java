package team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerServiceIT {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    PlayerRepository repository = new PlayerRepository(factory);

    PlayerService service = new PlayerService(repository);

    @BeforeEach
    void deleteData() {
        repository.deleteAll();
    }

    @Test
    void testSavePlayer() {
        Player player = new Player("John Doe", 100_000);
        service.save(player);

        assertThat(service.findAll())
                .extracting(Player::getName)
                .containsExactly("John Doe");
    }
}

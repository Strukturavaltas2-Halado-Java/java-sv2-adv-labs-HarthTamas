package team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerRepositoryIT {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    PlayerRepository repository = new PlayerRepository(factory);

    @BeforeEach
    void deleteData() {
        repository.deleteAll();
    }

    @Test
    void testSavePlayer() {
        Player player = new Player("John Doe", 100_000);
        repository.savePlayerWithoutTeam(player);

        assertThat(repository.findAll())
                .extracting(Player::getName)
                .containsExactly("John Doe");
    }


}

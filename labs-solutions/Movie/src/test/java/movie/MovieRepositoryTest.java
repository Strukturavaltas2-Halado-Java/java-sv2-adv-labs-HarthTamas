package movie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {

    MovieRepository repository;
    EntityManagerFactory factory;

    @BeforeEach
    void init() {
        factory = Persistence.createEntityManagerFactory("pu");
        repository = new MovieRepository(factory);
    }

    @AfterEach
    void end() {
        factory.close();
    }

    @Test
    void testSaveMovie() {
        Movie movie = repository.saveMovie(new Movie("Papirkutyák", LocalDate.of(2009, 02,19),89));
        assertThat(movie.getId()).isNotNull();
    }

    @Test
    void testFindByTitle() {
        Movie movie = repository.saveMovie(new Movie("Papirkutyák", LocalDate.of(2009, 02,19),89));
        Optional<Movie> result = repository.findByTitle("Papirkutyák");

        assertThat(result.get().getLength()).isEqualTo(89);
    }

}
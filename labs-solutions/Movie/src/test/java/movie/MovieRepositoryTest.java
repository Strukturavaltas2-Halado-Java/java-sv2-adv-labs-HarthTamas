package movie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @Test
    void testFindByTitleWithRatings() {
        Movie movie = new Movie("Papirkutyák", LocalDate.of(2009, 02,19),89);
        movie.addRating(new Rating(4.5,"Kuplung"));
        movie.addRating(new Rating(4.5,"Csumpi"));

        repository.saveMovie(movie);

        List<Movie> result = repository.findByTitleWithRatings("Papírkutyák");
        assertThat(result.get(0).getLength()).isEqualTo(89);
        assertThat(result.get(0).getRatings().size()).isEqualTo(2);
        assertThat(result.get(0).getRatings().stream().map(Rating::getUsername).toList()).isEqualTo(List.of("Kuplung","Csumpi"));
    }

}
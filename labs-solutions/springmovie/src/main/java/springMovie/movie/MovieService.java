package springMovie.movie;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private List<Movie> movies = new ArrayList<>();
    private ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDTO> getAllMovies() {
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    public MovieDTO createMovie(CreateMovieCommand command) {
        Movie movie = modelMapper.map(command, Movie.class);
        movie.setId((idGenerator.incrementAndGet()));
        movies.add(movie);
        return modelMapper.map(movie, MovieDTO.class);
    }

    public MovieDTO getMovieById(long id) {
        Movie movie = movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElseThrow(() -> new MovieNotFoundException("Movie not found " + id));
        return modelMapper.map(movie, MovieDTO.class);
    }

    public List<Integer> createRatingById(long id, CreateRatingCommand command) {
        Movie movie = movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElseThrow(() -> new MovieNotFoundException("Movie not found " + id));
        movie.getRatings().add(command.getRating());

        movie.setAverageRating(1.0 * movie.getRatings().stream()
                .mapToInt(i -> i)
                .sum()
                / movie.getRatings().size());
        return movie.getRatings();
    }


    public List<Integer> getRatingsById(long id) {
        Movie movie = movies.stream()
                .filter(m -> m.getId()==id)
                .findFirst().orElseThrow(()->new MovieNotFoundException("Movie not found "+id));
        return movie.getRatings();
    }


}

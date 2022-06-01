package springMovie.movie;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return service.getAllMovies();
    }

    @PostMapping
    public MovieDTO createMovie(@RequestBody CreateMovieCommand command) {
        return service.createMovie(command);
    }

    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable long id) {
        return service.getMovieById(id);
    }

    @GetMapping("/{id}/ratings")
    public List<Integer> getRatingsById(@PathVariable long id) {
        return service.getRatingsById(id);
    }

    @PostMapping("/{id}/ratings")
    public List<Integer> createRatingById(@PathVariable long id, @RequestBody CreateRatingCommand command) {
        if (command.getRating() > 0 && command.getRating() <= 5) {
            return service.createRatingById(id, command);
        } else {
            throw new IllegalArgumentException("Rating must be bigger than 1 and smaller than 5");
        }
    }

}

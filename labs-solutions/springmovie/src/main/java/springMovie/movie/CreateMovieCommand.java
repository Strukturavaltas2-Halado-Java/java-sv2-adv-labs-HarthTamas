package springMovie.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateMovieCommand {

    private String title;
    private int length;

}

package springMovie.movie;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private long id;
    private String title;
    private int length;
    private List<Integer> ratings = new ArrayList<>();
    private double averageRating;

    public Movie(long id, String title, int length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }
}

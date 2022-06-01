package springMovie.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private long id;
    private String title;
    private int length;
    private List<Integer> ratings = new ArrayList<>();
    private double averageRating;

}

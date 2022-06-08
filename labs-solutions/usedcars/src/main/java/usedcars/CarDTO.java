package usedcars;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    private long id;
    private String brand;
    private String type;
    private int age;
    private Condition condition;
    private List<KilometerState> states = new ArrayList<>();



}

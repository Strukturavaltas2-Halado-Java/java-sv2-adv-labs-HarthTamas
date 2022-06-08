package usedcars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
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

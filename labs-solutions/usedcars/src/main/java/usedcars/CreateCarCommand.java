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
public class CreateCarCommand {

    private String brand;
    private String type;
    private int age;
    private Condition condition;
    private List<KilometerState> state = new ArrayList<>();
}

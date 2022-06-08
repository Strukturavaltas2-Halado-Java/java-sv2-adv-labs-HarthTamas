package usedcars;

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
public class CreateCarCommand {

    private String brand;
    private String type;
    private int age;
    private Condition condition;
    private int kmState;
}

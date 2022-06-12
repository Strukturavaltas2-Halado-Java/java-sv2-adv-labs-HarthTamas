package usedcars.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import usedcars.model.CarCondition;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarCommand {


    private String brand;
    private String type;
    private int age;
    private CarCondition carCondition;
    private int kmState;
}

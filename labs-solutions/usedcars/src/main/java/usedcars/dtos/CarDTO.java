package usedcars.dtos;

import lombok.*;
import usedcars.model.CarCondition;
import usedcars.model.KilometerState;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    private Long id;
    private String brand;
    private String type;
    private int age;
    private CarCondition carCondition;
    private List<KilometerState> states = new ArrayList<>();



}

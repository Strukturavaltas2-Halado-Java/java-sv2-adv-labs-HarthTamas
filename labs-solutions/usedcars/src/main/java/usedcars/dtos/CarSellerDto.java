package usedcars.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import usedcars.model.Car;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarSellerDto {


    private Long id;
    private String sellerName;
    private List<CarDTO> cars = new ArrayList<>();

}

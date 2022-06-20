package usedcars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_seller")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarSeller {

    public CarSeller(String sellerName) {
        this.sellerName = sellerName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seller_name")
    private String sellerName;

    @OneToMany(mappedBy = "carSeller", cascade = CascadeType.REMOVE)
    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
        car.setCarSeller(this);
    }
}

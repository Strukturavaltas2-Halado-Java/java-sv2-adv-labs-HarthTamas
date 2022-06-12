package usedcars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String type;
    private int age;
    @Enumerated(EnumType.STRING)
    private CarCondition carCondition;

    @ElementCollection
    private List<KilometerState> states = new ArrayList<>();


    private int lastKilometerState;

    @ManyToOne
    @JoinColumn(name = "car_seller_id")
    private CarSeller carSeller;

    public Car(String brand, String type, int age, CarCondition carCondition) {
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.carCondition = carCondition;
    }

    public void addKilometerState(KilometerState kilometerState) {
        lastKilometerState = kilometerState.getKilometer();
        states.add(kilometerState);
    }

    public int getLastKilometerState() {
        return lastKilometerState;
    }


}

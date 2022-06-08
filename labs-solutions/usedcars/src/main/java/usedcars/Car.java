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
public class Car {


    private long id;
    private String brand;
    private String type;
    private int age;
    private Condition condition;
    private List<KilometerState> states = new ArrayList<>();
    private int lastKilometerState;

    public Car(long id, String brand, String type, int age, Condition condition) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.condition = condition;
    }

    public void addKilometerState(KilometerState kilometerState) {
        states.add(kilometerState);
    }

    public int getLastKilometerState() {
        lastKilometerState = states.get(states.size()-1).getKilometer();
        return lastKilometerState;
    }



}

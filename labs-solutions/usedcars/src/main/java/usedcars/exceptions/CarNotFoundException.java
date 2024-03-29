package usedcars.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CarNotFoundException extends AbstractThrowableProblem {

    public CarNotFoundException(long id) {
        super(URI.create("api/cars/car-not-found"),
                "Car not found",
                Status.NOT_FOUND,
                String.format("Car with id#%d not found",id)
        );
    }
}

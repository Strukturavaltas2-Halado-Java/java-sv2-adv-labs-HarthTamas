package usedcars.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class KilometerStateException extends AbstractThrowableProblem {
    public KilometerStateException(long id) {
        super(URI.create("api/cars/kilometer-states-not-acceptable"),
                "Kilometerstates have to be growing",
                Status.NOT_ACCEPTABLE,
                String.format("Kilometerstates have to be growing at car#%d",id)
        );
    }
}

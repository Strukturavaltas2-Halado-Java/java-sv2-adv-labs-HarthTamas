package usedcars.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CarSellerNotFoundException extends AbstractThrowableProblem {
    public CarSellerNotFoundException(Long id) {
        super(URI.create("api/car-sellers/seller-not-found"),
                "Seller not found",
                Status.NOT_FOUND,
                String.format("Seller with id#%d not found",id)
        );
    }
}

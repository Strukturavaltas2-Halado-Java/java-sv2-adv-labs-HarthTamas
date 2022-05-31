package bikesharing.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateBikeRentalCommand {

    private String bikeId;
    private String userId;
    private LocalDateTime deliveryTime;
    private double distance;

}

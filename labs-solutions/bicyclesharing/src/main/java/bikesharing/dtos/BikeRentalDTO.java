package bikesharing.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class BikeRentalDTO {

    private long id;
    private String bikeId;
    private String userId;
    private LocalDateTime deliveryTime;
    private double distance;
}

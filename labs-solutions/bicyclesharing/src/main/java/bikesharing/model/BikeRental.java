package bikesharing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BikeRental {

    private long id;
    private String bikeId;
    private String userId;
    private LocalDateTime deliveryTime;
    private double distance;

}

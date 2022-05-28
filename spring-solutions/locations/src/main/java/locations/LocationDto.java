package locations;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    private Long id;

    private String name;

    private double lat;

    private double lon;
}

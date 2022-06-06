package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class LocationDto {

    private Long id;
    @Schema(description = "Name of the location", example = "Budapest")
    private String name;

    @Schema(description = "Latitude of the location", example = "10.3")
    private double lat;

    @Schema(description = "Longitude of the location", example = "-5.2")
    private double lon;
}

package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateLocationCommand {

    @Schema(description = "Name of the location", example = "Budapest")
    private String name;

    @Schema(description = "Latitude of the location", example = "10.3")
    private double lat;

    @Schema(description = "Longitude of the location", example = "-5.2")
    private double lon;

}

package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class CreateLocationCommand {

    @Schema(description = "Name of the location", example = "Budapest")
    @NotBlank(message = "Location name cannot be blank")
    private String name;

    @Schema(description = "Latitude of the location", example = "10.3")
    @Min(value = -90, message = "Lat min value: -90")
    @Max(90)
    private double lat;

    @Schema(description = "Longitude of the location", example = "-5.2")
    @Min(-180)
    @Max(180)
    private double lon;

}

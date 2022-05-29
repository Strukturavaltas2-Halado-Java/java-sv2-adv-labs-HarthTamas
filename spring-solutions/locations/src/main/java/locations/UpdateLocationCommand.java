package locations;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateLocationCommand {

    private String name;

    private double lat;

    private double lon;

}

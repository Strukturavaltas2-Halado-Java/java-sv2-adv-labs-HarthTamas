package locations;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLocationCommand {

    private String name;

    private double lat;

    private double lon;

}

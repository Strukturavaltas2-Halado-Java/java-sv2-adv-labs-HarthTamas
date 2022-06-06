package locations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateLocationCommand {

    private String name;

    private double lat;

    private double lon;

}

package locations;

import java.awt.*;

public class LocationParser {
    public Location parse(String text) {
        String [] fields = text.split(",");
        return new Location(fields[0],Double.parseDouble(fields[1]),Double.parseDouble(fields[2]));
    }

}


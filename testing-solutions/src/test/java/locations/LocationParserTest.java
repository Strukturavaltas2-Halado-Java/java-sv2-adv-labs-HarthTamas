package locations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationParserTest {

    @Test
    void testParse() {
        assertEquals("Budapest", new LocationParser().parse("Budapest,47.497912,19.040235").getName());
    }



}
package locations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LocationRepeatTest {

    double [][] values = {{0,2,1},{81,45,0},{0,-34,1},{-34,45,0}};

    @RepeatedTest(value=4, name = "Is on equator? {currentRepetition}/{totalRepetitions}")
    @Test
     void testEquator(RepetitionInfo info) {
        Location location = new Location("Budapest",values[info.getCurrentRepetition()-1][0],values[info.getCurrentRepetition()-1][1]);
        assertEquals(values[info.getCurrentRepetition()-1][2] ==1,location.isOnEquator());
    }
}

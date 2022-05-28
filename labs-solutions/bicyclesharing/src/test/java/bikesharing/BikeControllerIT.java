package bikesharing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BikeControllerIT {

    @Autowired
    BikeController controller;

    @Test
    void testGetAllRentals() {
        Set<BikeRental> result = controller.getAllRentals();
        assertThat(result).extracting(BikeRental::getUserId).contains("US3334","US346");
    }

    @Test
    void testGetAllUserIds() {
        Set<String> result = controller.getAllUserIds();
        assertThat(result).contains("US3334","US346");
    }
}
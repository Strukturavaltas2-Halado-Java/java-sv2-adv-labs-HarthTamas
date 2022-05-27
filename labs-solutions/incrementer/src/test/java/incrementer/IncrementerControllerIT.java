package incrementer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IncrementerControllerIT {

    @Autowired
    IncrementerController controller;

    @Autowired
    IncrementerService service;

    @Autowired
    IncrementerRepository repository;

    @BeforeEach
    void init() {
        repository.setCounter(0);
    }

    @Test
    void testIncrement() {
        int expected = controller.increment();
        assertThat(expected).isEqualTo(1);
    }
}
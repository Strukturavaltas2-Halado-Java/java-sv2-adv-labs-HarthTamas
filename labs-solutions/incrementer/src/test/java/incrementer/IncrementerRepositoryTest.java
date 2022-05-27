package incrementer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncrementerRepositoryTest {

    @Test
    void testGetCounter() {
        IncrementerRepository repository = new IncrementerRepository();
        int expected = repository.getCounter();
        assertEquals(0,expected);
    }
}
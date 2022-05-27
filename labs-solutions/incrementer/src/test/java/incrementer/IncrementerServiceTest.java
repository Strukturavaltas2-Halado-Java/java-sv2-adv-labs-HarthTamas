package incrementer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncrementerServiceTest {

    @Mock
    IncrementerRepository repository;

    @InjectMocks
    IncrementerService service;


    @Test
    void testIncrement() {
        when(repository.getCounter()).thenReturn(4);
        int expected = service.increment();
        assertThat(expected).isEqualTo(4);
    }
}
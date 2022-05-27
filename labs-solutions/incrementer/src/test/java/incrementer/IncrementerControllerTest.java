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
class IncrementerControllerTest {

    @Mock
    IncrementerService service;

    @InjectMocks
    IncrementerController controller;

    @Test
    void testIncrement() {
    when(service.increment()).thenReturn(5);
    int expected = controller.increment();
    assertThat(expected).isEqualTo(5);
    }
}
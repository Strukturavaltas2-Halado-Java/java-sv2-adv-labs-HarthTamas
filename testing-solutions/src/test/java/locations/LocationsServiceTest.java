package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationsServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationsService locationsService;


    @Test
    void testIsEmptyWithoutMock() {
        assertThat(locationsService.calculateDistance("Paris","Budapest")).isEmpty();
    }

    @Test
    void testNoFirstLocation() {
        when(locationRepository.findByName("Budapest")).thenReturn(
                Optional.empty());

        when(locationRepository.findByName("Paris")).thenReturn(
                Optional.of(new Location("Paris", 48.87376, 2.25120)));

        assertThat(locationsService.calculateDistance("Budapest","Paris")).isEmpty();

        verify(locationRepository).findByName(argThat(locationName -> locationName.equals("Budapest")));
        verify(locationRepository).findByName(argThat(locationName -> locationName.equals("Paris")));
    }

    @Test
    void testNoSecondLocation() {
        when(locationRepository.findByName("Budapest")).thenReturn(Optional.of(new Location("Budapest",48.87376, 2.25120)));
        when(locationRepository.findByName("Paris")).thenReturn(Optional.empty());

        assertThat(locationsService.calculateDistance("Budapest","Paris")).isEmpty();

        verify(locationRepository).findByName(argThat(locationName -> locationName.equals("Budapest")));
        verify(locationRepository).findByName(argThat(locationName -> locationName.equals("Paris")));
    }

    @Test
    void testDistanceIsNull() {
        when(locationRepository.findByName("Budapest")).thenReturn(Optional.of(new Location("Budapest",48.87376, 2.25120)));

        assertThat(locationsService.calculateDistance("Budapest","Budapest").get()).isEqualTo(0);

        verify(locationRepository,times(2)).findByName(argThat(l->l.equals("Budapest")));
        verify(locationRepository,never()).findByName(argThat(l->l.equals("Paris")));
    }

    @Test
    void CalculateDistance() {
        assertThat(locationsService.calculateDistance("Budapest", "Paris")).isEmpty();

        when(locationRepository.findByName("Budapest")).thenReturn(Optional.of(new Location("Budapest",47.49791d, 19.04023d)));
        when(locationRepository.findByName("Paris")).thenReturn(Optional.of(new Location("Paris", 47.52997d, 21.63916d)));

        assertThat(locationsService.calculateDistance("Budapest", "Paris").get()).isCloseTo(195.2,within(0.01));

        verify(locationRepository,times(2)).findByName(argThat(l->l.equals("Budapest")));
        verify(locationRepository,never()).findByName(argThat(l->l.equals("Bukarest")));
        verify(locationRepository,times(2)).findByName(argThat(l->l.equals("Paris")));
    }

    @Test
    void testIsOnNorthernHemisphereTrue() {
        when(locationRepository.findLatitudeByName(any())).thenReturn(Optional.of(42.0));
        assertThat(locationsService.isOnNorthernHemisphere("Budapest")).isTrue();
        verify(locationRepository,times(1)).findLatitudeByName("Budapest");
        verify(locationRepository,never()).findByName(any());

    }
    @Test
    void testIsOnNorthernHemisphereFalse() {
        when(locationRepository.findLatitudeByName(any())).thenReturn(Optional.of(-45.0));
        assertThat(locationsService.isOnNorthernHemisphere("Budapest")).isFalse();
        verify(locationRepository).findLatitudeByName(argThat(l->l.equals("Budapest")));
        verify(locationRepository,never()).findByName(any());
    }

    @Test
    void testIsOnNorthernHemisphereThrowException() {
        when(locationRepository.findLatitudeByName(any())).thenReturn(Optional.empty());
        assertThatIllegalArgumentException().isThrownBy(()->locationsService.isOnNorthernHemisphere("Budapest"));
        verify(locationRepository).findLatitudeByName(argThat(l->l.equals("Budapest")));
        verify(locationRepository,never()).findByName(any());
    }





}

package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationControllerWebClientIT {

    @MockBean
    LocationService locationService;

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testGetAllLocations() {
        when(locationService.getLocations(any())).thenReturn(List.of(
                new LocationDto(1L, "Zanzibar", 0.3, 1.2),
                new LocationDto(2L, "Baltimore", 43.1, -11.8),
                new LocationDto(3L, "Budapest", 22.3, 11.2)
        ));

        webTestClient.get()
                .uri("/locations/all")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(LocationDto.class)
                .hasSize(3)
                .contains(new LocationDto(3L, "Budapest", 22.3, 11.2));
    }

    @Test
    void testFindLocationById() {
        when(locationService.findLocationById(1L)).thenReturn(new LocationDto(1L, "Zanzibar", 0.3, 1.2));

        webTestClient.get()
                .uri("locations/{id}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LocationDto.class).value(l -> assertEquals("Zanzibar", l.getName()));
    }

    @Test
    void testGetLocationsByParams() {
        when(locationService.getLocationsByAllParams(any(), any(), any(), any(), any())).thenReturn(List.of(
                new LocationDto(1L, "Banzibar", 0.3, 1.2),
                new LocationDto(2L, "Baltimore", 43.1, -11.8),
                new LocationDto(3L, "Budapest", 22.3, 11.2)
        ));
        webTestClient.get()
                .uri(builder -> builder.path("locations").queryParam("prefix", "B").build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(LocationDto.class)
                .hasSize(3)
                .contains(new LocationDto(3L, "Budapest", 22.3, 11.2));
    }

    @Test
    void testCreateLocation() {
        when(locationService.createLocation(any())).thenReturn(
                new LocationDto(1L, "Zanzibar", 0.3, 1.2));

        webTestClient.post()
                .uri("/locations")
                .bodyValue(new CreateLocationCommand("Zanzibar", 0.3, 1.2))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(LocationDto.class).value(l -> assertEquals("Zanzibar", l.getName()));
    }

    @Test
    void testUpdateLocation() {
        when(locationService.updateLocation(anyLong(), any())).thenReturn(
                new LocationDto(1L, "Zanzibar", 0.3, 1.2));
        webTestClient.put()
                .uri("/locations/{id}",1)
                .bodyValue(new CreateLocationCommand("Zanzibar", 0.3, 1.2))
                .exchange()
                .expectStatus().isOk()
                .expectBody(LocationDto.class).value(l -> assertEquals("Zanzibar", l.getName()));
    }

    @Test
    void testDeleteLocation() {
        webTestClient.delete()
                .uri("/locations/{id}",1)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testCreateLocationWithInvalidData() {
//        when(locationService.createLocation(any())).thenReturn(
//                new LocationDto(1L, "Zanzibar", 0.3, 1.2));

        webTestClient.post()
                .uri("/locations")
                .bodyValue(new CreateLocationCommand("", 0.3, 1.2))
                .exchange()
                .expectStatus().isBadRequest();
    }


}


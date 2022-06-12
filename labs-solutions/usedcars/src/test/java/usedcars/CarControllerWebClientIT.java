package usedcars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import usedcars.dtos.CarDTO;
import usedcars.model.CarCondition;
import usedcars.model.KilometerState;
import usedcars.service.CarSellingService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerWebClientIT {

    @MockBean
    CarSellingService service;

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testGetCarById() {
        CarDTO carDTO = new CarDTO(1L, "Suzuki", "Swift", 6, CarCondition.NORMAL,
                List.of(new KilometerState(234, LocalDate.now())));
        when(service.getCarById(anyLong())).thenReturn(carDTO);

        webTestClient.get()
                .uri("api/cars/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CarDTO.class).value(l -> assertEquals(234, l.getStates().get(0).getKilometer()));
    }

    @Test
    void testCreateCar() {
//        CarDTO carDTO = new CarDTO(1L, "Suzuki", "Swift", 6, Condition.NORMAL,
//                List.of(new KilometerState(234, LocalDate.now())));
//        when(service.createCar(any())).thenReturn(carDTO);
//
//        webTestClient.post()
//                .uri("api/cars")
//                .bodyValue(new CreateCarCommand())
//                .exchange()
//                .expectStatus().isCreated()
////                .expectBody(CarDTO.class).isEqualTo(carDTO);
//                .expectBody(CarDTO.class)
//                .value(carDTO1 -> assertThat(carDTO.getBrand())).isEqualTo("Suzuki");
    }

    @Test
    void testDeleteCar() {
        webTestClient.delete()
                .uri("api/cars/{id}", 1)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testGetAllBrandsInAlphabeticalOrder() {
        when(service.getAllBrandsInAlphabeticalOrder()).thenReturn(Set.of("Hyundai", "Opel", "Trabant"));
        webTestClient.get()
                .uri("/api/cars/brands")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TreeSet.class)
                .hasSize(2);

//                .contains("Opel");
    }

    @Test
    void testGetCars() {
        when(service.getCars(any(), any(), any())).thenReturn(List.of(
                        new CarDTO(1L, "ASuzuki", "ASwift", 6, CarCondition.NORMAL,
                                List.of(new KilometerState(2, LocalDate.now()))),
                        new CarDTO(2L, "BSuzuki", "BSwift", 5, CarCondition.NORMAL,
                                List.of(new KilometerState(23, LocalDate.now()))),
                        new CarDTO(3L, "CSuzuki", "CSwift", 4, CarCondition.NORMAL,
                                List.of(new KilometerState(234, LocalDate.now())))
                )
        );

        webTestClient.get()
                .uri(builder -> builder.path("api/cars").queryParam("brand", "valami").build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CarDTO.class)
                .hasSize(3)
                .contains(new CarDTO(1L, "ASuzuki", "ASwift", 6, CarCondition.NORMAL,
                        List.of(new KilometerState(2, LocalDate.now()))));
    }
}

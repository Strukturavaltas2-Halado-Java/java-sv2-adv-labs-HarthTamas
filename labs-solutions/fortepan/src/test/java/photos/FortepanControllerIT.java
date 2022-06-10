package photos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FortepanControllerIT {

    @Autowired
    WebTestClient webClient;

    @Autowired
    FortepanService service;

    PhotoDto photo;

    @BeforeEach
    void init() {
        service.deleteAllPhotos();

        service.createPhotoWithDescriptionAndYear(new CreatePhotoWithDescriptionAndYearCommand("Ló a mezőn", 1921));
        photo = service.createPhotoWithDescription(new CreatePhotoWithDescriptionCommand("Nő fák alatt, napernyővel"));
        service.updatePhotoWithPhotographerAndYear(photo.getId(), new UpdatePhotoWithPhotographerAndYearCommand(1935, "Tóth Béla"));
        service.createPhotoWithDescription(new CreatePhotoWithDescriptionCommand("Örkény István a feleségével"));
    }

    @Test
    void testListAllPhotos() {
        // Itt írd meg a WebClient-es GET kérést és vizsgálj rá a visszérkezett PhotoDto listára!
    }

    @Test
    void testListAllPhotosWithParameter() {
        // Itt írd meg a WebClient-es GET kérést a photo nevű attribútum adataiból vett paraméterekkel,
        // és vizsgálj rá a visszérkezett PhotoDto listára!
    }

    @Test
    void testFindById() {
        // Itt írd meg a WebClient-es GET kérést a photo nevű attribútum id-jával,
        // és vizsgálj rá a visszérkezett PhotoDto objektumra!
    }

    @Test
    void testCreatePhotoWithDescription() {
        // Itt hozz létre úgy egy új fényképet, hogy a WebClient segítségével küldöd be a POST kérést, amely csak egy
        // leírást tartalmaz!
        // (Tehát nem a service valamelyik createX() metódusát hívod meg, mint ahogy a @BeforeEach metódusban látható.)
        // Vizsgáld is a visszaérkező objektumot!
    }

    @Test
    void testCreatePhotoWithDescriptionAndYear() {
        // Itt hozz létre úgy egy új fényképet, hogy a WebClient segítségével küldöd be a POST kérést, amely egy
        // leírást és egy évszámot tartalmaz!
        // (Tehát nem a service valamelyik createX() metódusát hívod meg, mint ahogy a @BeforeEach metódusban látható.)
        // Vizsgáld is a visszaérkező objektumot!
    }

    @Test
    void testUpdatePhotoWithPhotographerAndYear() {
        // Írd meg a photo nevű attribútum módosítását a fotós nevével és az évszámmal,
        // és vizsgálj rá a visszakapott PhotoDto objektumra!
    }

    @Test
    void updatePhotoWithInfo() {
        // Írd meg a photo nevű attribútum módosítását valamilyen plusz infóval,
        // és vizsgálj rá a visszakapott PhotoDto objektumra!
    }

    @Test
    void deletePhoto() {
        // Írd meg a photo nevű attribútum törlését, majd vizsgálj rá, hogy tényleg
        // törlődött-e a listából (kérd le újra az összes elemet)!
    }
}
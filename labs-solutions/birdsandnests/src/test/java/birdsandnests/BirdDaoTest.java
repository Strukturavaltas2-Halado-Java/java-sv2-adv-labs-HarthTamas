package birdsandnests;

import org.junit.jupiter.api.*;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Bird database operations test")
class BirdDaoTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
    BirdDao birdDao = new BirdDao(factory);
    NestDao nestDao = new NestDao(factory);

    @BeforeEach
    void init() {
        Nest nestingBox1 = new NestingBox(4, 13, 21);
        nestDao.saveNest(nestingBox1);
        Nest nestingBox2 = new NestingBox(4, 10, 18);
        nestDao.saveNest(nestingBox2);
        Bird bird1 = new Bird(BirdSpecies.OWL, nestingBox1);
        Bird bird2 = new Bird(BirdSpecies.OWL,nestingBox2);
        birdDao.saveBird(bird1);
        birdDao.saveBird(bird2);

        Nest roundNest = new RoundNest(3, 40);
        nestDao.saveNest(roundNest);
        Bird bird3 = new Bird(BirdSpecies.STORK, roundNest);
        birdDao.saveBird(bird3);

        Nest swallowNest = new SwallowNest(5,7);
        nestDao.saveNest(swallowNest);
        Bird bird4 = new Bird(BirdSpecies.SWALLOW,swallowNest);
        birdDao.saveBird(bird4);
    }

    @AfterEach
    void end() {
        factory.close();
    }

    @Test
    @DisplayName("Test listing birds")
    void testListBirds() {
        List<Bird> birds = birdDao.listBirds();
        assertThat(birds)
                .hasSize(4)
                .extracting(Bird::getSpecies)
                .contains(BirdSpecies.OWL)
                .contains(BirdSpecies.SWALLOW)
                .contains(BirdSpecies.STORK)
                .filteredOn(b -> b == BirdSpecies.OWL)
                .hasSize(2);
    }

    @Test
    @DisplayName("Test listing birds with given species")
    void testSaveBirdAndListSpeciesGiven() {
        List<Bird> owlBirds = birdDao.listBirdsSpeciesGiven(BirdSpecies.OWL);
        assertThat(owlBirds)
                .hasSize(2)
                .extracting(Bird::getSpecies)
                .containsOnly(BirdSpecies.OWL);
    }

    @RepeatedTest(value = 3, name = "count: {currentRepetition} from {totalRepetitions}")
    @DisplayName("Test listing birds with given number of eggs")
    void testListBirdsWithEggsGiven(RepetitionInfo info) {
        int[][] values = {{4, 2}, {3, 1}, {5, 1}};
        int i = info.getCurrentRepetition();

        assertEquals(values[i - 1][1], birdDao.listBirdsWithEggsGiven(values[i - 1][0]).size());
    }

    @Test
    @DisplayName("Test deleting bird from database")
    void testDeleteBird() {
        Bird bird = new Bird();
        birdDao.saveBird(bird);

        assertEquals(5, birdDao.listBirds().size());

        birdDao.deleteBird(bird.getId());

        assertEquals(4, birdDao.listBirds().size());
    }

}
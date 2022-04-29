package activitytracker;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TrackPointDaoTest {

    EntityManagerFactory factory;
    TrackPointDao trackPointDao;
    ActivityDao activityDao;

    @BeforeEach
    void init() {
        factory = Persistence.createEntityManagerFactory("pu");
        trackPointDao = new TrackPointDao(factory);
        activityDao = new ActivityDao(factory);
    }

    @Test
    void testSaveTrackPoint() {
        TrackPoint trackPoint = new TrackPoint(34.2,0.34);
        trackPointDao.saveTrackPoint(trackPoint);
        Long id = trackPoint.getId();
        TrackPoint trackPointFound = trackPointDao.findTrackPointById(id);
        assertThat(trackPointFound.getLatitude()).isEqualTo(0.34);
    }

    @AfterEach
    void end() {
        factory.close();
    }

}
package activitytracker;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

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

    @AfterEach
    void end() {
        factory.close();
    }

    @Test
    void testSaveTrackPoint() {
        TrackPoint trackPoint = new TrackPoint(34.2,0.34);
        trackPointDao.saveTrackPoint(trackPoint);
        Long id = trackPoint.getId();
        TrackPoint trackPointFound = trackPointDao.findTrackPointById(id);
        assertThat(trackPointFound.getLatitude()).isEqualTo(0.34);
    }

    @Test
    void testFindTrackPointCoordinatesByDate() {
        Activity activityHiking = new Activity(ActivityType.HIKING, "Túra a hegyekben", LocalDateTime.of(2022, Month.MARCH, 20, 10, 20));
        Activity activityRunning1 = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        Activity activityRunning2 = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.JANUARY, 15, 10, 20));
        Activity activityRunning3 = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 10, 10, 20));
        TrackPoint tr1 = new TrackPoint(45.454, 15.56);
        TrackPoint tr2 = new TrackPoint(5.454, -45.56);
        TrackPoint tr3 = new TrackPoint(0.454, -5.56);

        activityRunning1.addTrackPoint(tr1);
        activityRunning2.addTrackPoint(tr2);
        activityRunning3.addTrackPoint(tr3);

        activityDao.saveActivity(activityHiking);
        activityDao.saveActivity(activityRunning1);
        activityDao.saveActivity(activityRunning2);
        activityDao.saveActivity(activityRunning3);

        List<Coordinate> coordinates = trackPointDao.findTrackPointCoordinatesByDate(LocalDateTime.of(2022, 4, 1, 1, 1, 1), 0, 10);
        assertThat(coordinates.get(0).getLatitude()).isEqualTo(15.56);
        assertThat(coordinates.get(1).getLongitude()).isEqualTo(0.454);
    }



}
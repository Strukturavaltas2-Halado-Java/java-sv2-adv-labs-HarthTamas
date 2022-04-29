package activitytracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {

    EntityManagerFactory factory;
    ActivityDao activityDao;

    @BeforeEach
    void init() {
        factory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(factory);
    }

    @AfterEach
    void close() {
        factory.close();
    }

    @Test
    void testSaveActivity() {
        Activity activityHiking = new Activity(ActivityType.HIKING, "Túra a hegyekben", LocalDateTime.of(2022, Month.MARCH, 20, 10, 20));
        activityDao.saveActivity(activityHiking);
        Long id = activityHiking.getId();
        Activity activityFound = activityDao.findActivityById(id);
        assertEquals("Túra a hegyekben", activityFound.getDescription());
    }

    @Test
    void testListActivities() {
        EntityManager em = factory.createEntityManager();
        Activity activityHiking = new Activity(ActivityType.HIKING, "Túra a hegyekben", LocalDateTime.of(2022, Month.MARCH, 20, 10, 20));
        Activity activityRunning = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        em.getTransaction().begin();
        em.persist(activityHiking);
        em.persist(activityRunning);
        em.getTransaction().commit();

        List<Activity> activities = activityDao.listActivities();
        List<String> activityDescriptions = activities.stream().map(Activity::getDescription).toList();

        assertEquals(List.of("Futás előre", "Túra a hegyekben"), activityDescriptions);
        em.close();
    }

    @Test
    void testFindActivityById() {
        EntityManager em = factory.createEntityManager();
        Activity activityRunning = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        activityDao.saveActivity(activityRunning);

        Long id = activityRunning.getId() +1;
        Activity activityFound = activityDao.findActivityById(id);
        System.out.println(activityFound);
//        assertThat(activityRunning.getDescription()).isEqualTo(activityFound.getDescription());

        em.close();
    }

    @Test
    void testDeleteActivity() {
        Activity activityRunning = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        activityDao.saveActivity(activityRunning);
        Long id = activityRunning.getId();
        activityDao.deleteActivity(id);

        List<Activity> activities = activityDao.listActivities();
        assertTrue(activities.isEmpty());
    }

    @Test
    void testSaveAndUpdate() {
        Activity activityRunning = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        activityDao.saveActivity(activityRunning);
        assertTrue(activityRunning.getCreatedAt() != null);

        Long id = activityRunning.getId();
        activityDao.updateActivity(id, "Futás hátra");

        Activity expected = activityDao.findActivityById(id);
        assertEquals("Futás hátra", expected.getDescription());
        assertEquals(ActivityType.RUNNING, expected.getType());
        assertThat(expected.getStartTime()).isEqualTo(LocalDateTime.of(2022,Month.APRIL,18,10,20));
    }

    @Test
    void testFindActivityByIdWithLabels() {
        Activity activityRunning = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        activityRunning.setLabels(List.of("futás", "valamerre"));
        activityDao.saveActivity(activityRunning);

        Activity activityFound = activityDao.findActivityByIdWithLabels(activityRunning.getId());
        assertThat(activityFound.getLabels().get(0)).isEqualTo("futás");
        assertThat(activityFound.getLabels().get(1)).isEqualTo("valamerre");
    }

    @Test
    void testTrackPoint() {
        TrackPoint tr1 = new TrackPoint(45.454,15.56);
        TrackPoint tr2 = new TrackPoint(5.454,-45.56);
        Activity activity = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        activity.addTrackPoint(tr1);
        activity.addTrackPoint(tr2);
        activityDao.saveActivity(activity);
        Long id = activity.getId();
        Activity activityFound = activityDao.findActivityByIdWithTrackPoints(id);
        assertThat(activityFound.getTrackPoints().get(0).getLatitude()).isEqualTo(15.56);
    }

    @Test
    void testAddTrackPoints() {
        Activity activity = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        activityDao.saveActivity(activity);
        activityDao.addTrackPoint(activity.getId(), new TrackPoint(0.43,-8.89));
        Activity activityFound = activityDao.findActivityByIdWithTrackPoints(activity.getId());
        assertThat(activityFound.getTrackPoints().get(0).getLatitude()).isEqualTo(-8.89);
    }

    @Test
    void testTrackPointOrder() {
        TrackPoint tr1 = new TrackPoint(LocalDate.of(2022,12,12),45.454,15.56);
        TrackPoint tr2 = new TrackPoint(LocalDate.of(2019,1,5),5.454,-45.56);
        TrackPoint tr3= new TrackPoint(LocalDate.of(2020,2,12),45.454,5.56);
        Activity activity = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        activity.addTrackPoint(tr2);
        activity.addTrackPoint(tr3);
        activity.addTrackPoint(tr1);
        activityDao.saveActivity(activity);
        Long id = activity.getId();
        Activity activityFound = activityDao.findActivityByIdWithTrackPoints(id);
        assertThat(activityFound.getTrackPoints().get(0).getLatitude()).isEqualTo(-45.56);
    }
}
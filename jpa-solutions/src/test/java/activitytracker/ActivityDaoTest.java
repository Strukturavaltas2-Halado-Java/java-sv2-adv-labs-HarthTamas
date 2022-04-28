package activitytracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.jar.JarOutputStream;

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

        Long id = activityRunning.getId();

        Activity activityFound = activityDao.findActivityById(id);
        assertThat(activityRunning.getDescription()).isEqualTo(activityFound.getDescription());

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
        activity.setTrackPoints(Set.of(tr1,tr2));
        activityDao.saveActivity(activity);
    }
}
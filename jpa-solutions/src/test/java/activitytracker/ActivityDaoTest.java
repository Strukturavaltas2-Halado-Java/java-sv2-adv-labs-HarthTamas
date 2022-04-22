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
        List<String> activityDescriptions =  activities.stream().map(Activity::getDescription).toList();

        assertEquals(List.of("Futás előre","Túra a hegyekben"), activityDescriptions);
        em.close();
    }

    @Test
    void testFindActivityById() {
        EntityManager em = factory.createEntityManager();
        Activity activityRunning = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        activityDao.saveActivity(activityRunning);

        Long id = activityRunning.getId();

        Activity activityFound = activityDao.findActivityById(id);
        assertEquals(activityRunning.getDescription(), activityFound.getDescription());

        em.close();
    }

    @Test
    void testDeleteActivity() {
    }

}
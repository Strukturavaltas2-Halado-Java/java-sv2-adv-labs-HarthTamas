package activitytracker;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {

    @Test
    void testSaveActivity() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        ActivityDao activityDao = new ActivityDao(factory);
        Activity activityHiking = new Activity(ActivityType.HIKING, "Túra a hegyekben", LocalDateTime.of(2022, Month.MARCH, 20, 10, 20));
        activityDao.saveActivity(activityHiking);

        Long id = activityHiking.getId();
        Activity activityFound = activityDao.findActivityById(id);

        assertEquals("Túra a hegyekben",activityFound.getDescription());
    }




}
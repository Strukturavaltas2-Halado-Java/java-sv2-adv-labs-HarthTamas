package activitytracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class AreaDaoTest {

    private AreaDao areaDao;
    private ActivityDao activityDao;
    private EntityManagerFactory factory;

    @BeforeEach
    void init() {
        factory = Persistence.createEntityManagerFactory("pu");
        areaDao = new AreaDao(factory);
        activityDao = new ActivityDao(factory);
    }

    @AfterEach
    void end() {
        factory.close();
    }

    @Test
    public void testSaveAreaAndFindAreaByName() {

        Activity activityRun = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        Activity activityHiking = new Activity(ActivityType.HIKING, "Túra a hegykebe", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        Activity activityBiking = new Activity(ActivityType.BIKING, "Bringa körbe", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));

        activityDao.saveActivity(activityRun);
        activityDao.saveActivity(activityBiking);
        activityDao.saveActivity(activityHiking);

        Area baranya = new Area("Baranya");
        Area pest = new Area("Pest");
        Area somogy = new Area("Somogy");

        baranya.addActivity(activityRun);
        baranya.addActivity(activityHiking);
        baranya.addActivity(activityBiking);

        pest.addActivity(activityBiking);

        somogy.addActivity(activityHiking);
        somogy.addActivity(activityBiking);

        areaDao.saveArea(baranya);
        areaDao.saveArea(somogy);
        areaDao.saveArea(pest);

        Area areaFound = areaDao.findAreaByName("Baranya");

        assertThat(areaFound.getActivities().size()).isEqualTo(3);
        assertThat(areaFound.getActivities().stream()
                .sorted(Comparator.comparing(Activity::getDescription)).map(Activity::getDescription).findFirst().get()).isEqualTo("Bringa körbe");
    }

    @Test
    void testSaveAndFindAreaAndCities() {
        Area area = new Area("Baranya");
        City debrecen = new City("Debrecen", 225000);
        City pecs = new City("Pecs", 178000);
        area.getCities().put(debrecen.getName(), debrecen);
        area.getCities().put(pecs.getName(), pecs);

        areaDao.saveArea(area);
        Long id = area.getId();

        Area found = areaDao.findAreaById(id);
        assertThat(found.getCities().get(debrecen.getName()).getPopulation()).isEqualTo(225000);
        assertThat(found.getCities().get("Pecs").getName()).isEqualTo("Pecs");
    }

}

package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class ActivityTrackerMain {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

        saveActivities(factory);
        EntityManager em = factory.createEntityManager();

        Long id = 2l;
        Activity activity = em.find(Activity.class, id);
        System.out.println(activity);

        List<Activity> activities = em.createQuery("select e from Activity e", Activity.class).getResultList();
        System.out.println(activities);

        em.getTransaction().begin();
        activity.setDescription("Nem is körbe tekertünk, hanem egyenesen");
        em.getTransaction().commit();
        activity = em.find(Activity.class, id);
        System.out.println(activity);

        em.getTransaction().begin();
        em.remove(activity);
        em.getTransaction().commit();

        activities = em.createQuery("select e from Activity e", Activity.class).getResultList();
        System.out.println(activities);

        em.close();
        factory.close();
    }

    private static void saveActivities(EntityManagerFactory factory) {
        EntityManager em = factory.createEntityManager();

        Activity activityHiking = new Activity(ActivityType.HIKING, "Túra a hegyekben", LocalDateTime.of(2022, Month.MARCH, 20, 10, 20));
        Activity activityRunning = new Activity(ActivityType.RUNNING, "Futás előre", LocalDateTime.of(2022, Month.APRIL, 18, 10, 20));
        Activity activityBiking = new Activity(ActivityType.BIKING, "Tekerés körbekörbe", LocalDateTime.of(2022, Month.APRIL, 16, 10, 20));
        Activity activityBasketball = new Activity(ActivityType.BASKETBALL, "Vár a grund", LocalDateTime.of(2022, Month.MARCH, 2, 10, 20));

        em.getTransaction().begin();
        em.persist(activityHiking);
        em.persist(activityBiking);
        em.persist(activityRunning);
        em.persist(activityBasketball);
        em.getTransaction().commit();

        em.close();
    }
}

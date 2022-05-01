package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityDao {

    EntityManagerFactory factory;

    public ActivityDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveActivity(Activity activity) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
        em.close();
    }

    public List<Activity> listActivities() {
        EntityManager em = factory.createEntityManager();
        List<Activity> result = em.createQuery("select a from Activity a order by a.description", Activity.class).getResultList();
        em.close();
        return result;
    }

    public Activity findActivityById(long id) {
        EntityManager em = factory.createEntityManager();
        Activity activity = em.find(Activity.class, id);
        em.close();
        return activity;
    }

    public void deleteActivity(long id) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.getReference(Activity.class, id);
        em.remove(activity);
        em.getTransaction().commit();
        em.close();
    }

    public void updateActivity(long id, String description) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.find(Activity.class, id);
        activity.setDescription(description);
        em.getTransaction().commit();
        em.close();
    }

    public Activity findActivityByIdWithLabels(long id) {
        EntityManager em = factory.createEntityManager();
        Activity activity = em.createQuery("select a from Activity a left join fetch a.labels where a.id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }

    public Activity findActivityByIdWithTrackPoints(long id) {
        EntityManager em = factory.createEntityManager();
        Activity activity = em.createQuery("select a from Activity a join fetch a.trackPoints where a.id = :id", Activity.class)
                .setParameter("id",id)
                .getSingleResult();
        em.close();
        return activity;
    }

    public void addTrackPoint(long id, TrackPoint trackPoint) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.find(Activity.class,id);
        trackPoint.setActivity(activity);
        em.persist(trackPoint);
        em.getTransaction().commit();
        em.close();
    }

    public List<Object[]> findTrackPointCountByActivity() {
        EntityManager em = factory.createEntityManager();
        List<Object[]> result =
                em.createQuery("select a.description, count(t) from Activity a join a.trackPoints t group by a order by a.description")
                .getResultList();
        em.close();
        return result;
    }

}

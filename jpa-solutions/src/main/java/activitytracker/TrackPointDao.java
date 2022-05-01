package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

public class TrackPointDao {

    EntityManagerFactory factory;

    public TrackPointDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public TrackPoint saveTrackPoint(TrackPoint trackPoint) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(trackPoint);
        em.getTransaction().commit();
        em.close();
        return trackPoint;
    }

    public TrackPoint findTrackPointById(long id) {
        EntityManager em = factory.createEntityManager();
        TrackPoint trackPoint = em.createQuery("select t from TrackPoint  t where t.id=:id", TrackPoint.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return trackPoint;
    }

    public List<Coordinate> findTrackPointCoordinatesByDate(LocalDateTime afterThis, int start, int max) {
        EntityManager em = factory.createEntityManager();
        List<Coordinate> coordinates = em.createNamedQuery("findTrackPointCoordinatesByDate")
                .setParameter("afterThis", afterThis)
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();
        em.close();
        return coordinates;
    }
}

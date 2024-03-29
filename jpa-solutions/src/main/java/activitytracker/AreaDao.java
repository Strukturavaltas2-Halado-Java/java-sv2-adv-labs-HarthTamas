package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

public class AreaDao {

    private EntityManagerFactory factory;

    public AreaDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Area saveArea(Area area) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(area);
        em.getTransaction().commit();
        em.close();
        return area;
    }

    public Area findAreaByName(String name) {
        EntityManager em = factory.createEntityManager();
        Area area = em.createQuery("select a from Area a join fetch a.activities where a.name = :name", Area.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return area;
    }

    public Area findAreaById(long id) {
        EntityManager em = factory.createEntityManager();
        Area area = em.createQuery("select a from Area a join fetch a.cities where a.id = :id", Area.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return area;
    }
}

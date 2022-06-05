package birdsandnests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class NestDao {

    private EntityManagerFactory factory;

    public NestDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveNest(Nest nest) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nest);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Nest findNestById(long id) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.find(Nest.class, id);
        } finally {
            em.close();
        }
    }

    public Nest findNestWithMinBirds() {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select distinct n from Nest n left join fetch n.birds where n.birds.size = " +
                    "(select min(n.birds.size) from Nest n)", Nest.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    public long countNestsWithEggsGiven(int eggs) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select COUNT(n) from Nest n where n.numberOfEggs = :eggs",Long.class)
                    .setParameter("eggs", eggs)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }



}

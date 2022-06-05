package birdsandnests;

import org.junit.jupiter.api.DisplayName;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@DisplayName("Bird database operations test")
public class BirdDao {

    private EntityManagerFactory factory;

    public BirdDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveBird(Bird bird) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bird);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Bird> listBirds() {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select b from Bird b", Bird.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Bird> listBirdsSpeciesGiven(BirdSpecies species) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select b from Bird b where b.species = :species", Bird.class)
                    .setParameter("species", species)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Bird> listBirdsWithEggsGiven(int eggs) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select b from Bird b where b.nest.numberOfEggs = :eggs", Bird.class)
                    .setParameter("eggs", eggs)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void deleteBird(long id) {
        EntityManager em = factory.createEntityManager();
        try {
            Bird birdToDelete = em.getReference(Bird.class, id);
            em.getTransaction().begin();
            em.remove(birdToDelete);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}

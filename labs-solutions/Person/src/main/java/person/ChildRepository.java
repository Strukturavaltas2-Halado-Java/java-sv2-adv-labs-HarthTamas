package person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ChildRepository {

    EntityManagerFactory factory;

    public ChildRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Child saveChild(Child  child) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(child);
        em.getTransaction().commit();
        em.close();
        return child;
    }

    public Child findChildById(long id) {
        EntityManager em = factory.createEntityManager();
        Child found = em.find(Child.class,id);
        em.close();
        return found;
    }


}

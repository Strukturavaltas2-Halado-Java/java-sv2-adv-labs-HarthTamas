package person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersonRepository {

    EntityManagerFactory factory;

    public PersonRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Person savePerson(Person person) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
        return person;
    }

    public Person findPersonById(long id) {
        EntityManager em = factory.createEntityManager();
        Person found = em.find(Person.class,id);
        em.close();
        return found;
    }

}

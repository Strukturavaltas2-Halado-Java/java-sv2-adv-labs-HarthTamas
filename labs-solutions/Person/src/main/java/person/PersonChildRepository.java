package person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class PersonChildRepository {

    EntityManagerFactory factory;

    public PersonChildRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public List<Child> findChildYearOfBirthAfter(int year) {
        EntityManager em = factory.createEntityManager();
        List<Child> result = em.createQuery("select c from Child  c where c.yearOfBirth > :year")
                .setParameter("year", year)
                .getResultList();
        em.close();
        return result;
    }

    public List<Person> findPersonWithTwoOrMoreChildren() {
        EntityManager em = factory.createEntityManager();
        List<Person> result = em.createQuery("select p from Person p where p.children.size >= 2", Person.class)
                .getResultList();
        em.close();
        return result;
    }

    public Person findPersonWithMostChildren() {
        EntityManager em = factory.createEntityManager();
        Person result = em.createQuery("select p from Person p where p.children.size = (select max(p.children.size) from Person p)", Person.class)
                .getSingleResult();
        em.close();
        return result;
    }

    public Child findChildWithPersonNameAndBornInYear(String personName, int year) {
        EntityManager em = factory.createEntityManager();
        Child result = em.createQuery("select c from Child c where c.person.name = :name and c.yearOfBirth = :year", Child.class)
                .setParameter("name", personName)
                .setParameter("year", year)
                .getSingleResult();
        em.close();
        return result;
    }

    public Person findPersonWithChild(String name) {
        EntityManager em = factory.createEntityManager();
        Person result = em.createQuery("select c.person from Child c where c.name = :name", Person.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return result;
    }

    public double findAverageChildNumber() {
        EntityManager em = factory.createEntityManager();
        double result = em.createQuery("select AVG(p.children.size) from Person p", Double.class)
                .getSingleResult();
        em.close();
        return result;
    }

    public List<Child> findChildrenWithMostBrothers() {
        EntityManager em = factory.createEntityManager();
        List<Child> result = em.createQuery("select c from Child c where c.person.children.size = (select max(p.children.size) from Person p)")
                .getResultList();
        em.close();
        return result;
    }

}

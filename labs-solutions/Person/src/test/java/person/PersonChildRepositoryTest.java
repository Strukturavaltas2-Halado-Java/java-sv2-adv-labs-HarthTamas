package person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonChildRepositoryTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    ChildRepository childRepository = new ChildRepository(factory);
    PersonRepository personRepository = new PersonRepository(factory);

    PersonChildRepository personChildRepository =  new PersonChildRepository(factory);

    Child lacigyerek;
    Child liligyerek;
    Child belagyerek;
    Child tomigyerek;
    Child gabigyerek;
    Child marigyerek;


    Person barbianya;
    Person monianya;
    Person jucianya;

    @BeforeEach
    void init() {
        jucianya = new Person("Juci", 25);
        barbianya = new Person("Barbi", 32);
        monianya = new Person("Móni", 45);
        lacigyerek = new Child("Lacika", 2016);
        liligyerek = new Child("Lilike",2017);
        belagyerek = new Child("Bélus",2014);
        tomigyerek = new Child("Tomika",2012);
        gabigyerek = new Child("Gabesz",2015);
        marigyerek = new Child("Mari",2016);

        monianya.addChild(lacigyerek);
        monianya.addChild(liligyerek);
        jucianya.addChild(tomigyerek);
        barbianya.addChild(belagyerek);
        barbianya.addChild(gabigyerek);
        barbianya.addChild(marigyerek);

        personRepository.savePerson(jucianya);
        personRepository.savePerson(barbianya);
        personRepository.savePerson(monianya);
    }

    @Test
    void testFindChildYearOfBirthAfter() {
        List<Child> expected = personChildRepository.findChildYearOfBirthAfter(2014);
        assertEquals(4,expected.size());
    }

    @Test
    void testFindPersonWithTwoOrMoreChildren() {
        List<Person> result = personChildRepository.findPersonWithTwoOrMoreChildren();
        assertEquals(2,result.size());
    }

    @Test
    void testFindPersonWithMostChildren() {
        Person expected = personChildRepository.findPersonWithMostChildren();
        assertThat(expected.getName()).isEqualTo("Barbi");
    }

    @Test
    void testFindChildWithPersonNameAndBornInYear() {
        Child expected = personChildRepository.findChildWithPersonNameAndBornInYear("Barbi", 2015);
        assertThat(expected.getName()).isEqualTo("Gabesz");
    }

    @Test
    void testFindPersonWithChild() {
        Person expected = personChildRepository.findPersonWithChild("Mari");
        assertThat(expected.getName()).isEqualTo("Barbi");
    }

    @Test
    void testFindAverageChildNumber() {
        Double result = personChildRepository.findAverageChildNumber();
        assertEquals(2.0,result);
    }

    @Test
    void testFindChildrenWithMostBrothers() {
        List<Child> expected = personChildRepository.findChildrenWithMostBrothers();
        assertEquals(3,expected.size());
    }
}
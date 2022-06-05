package person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    PersonRepository repository = new PersonRepository(factory);

    Person barbi;
    Person laci;
    Person moni;
    Person juci;

    @BeforeEach
    void init() {
        juci = new Person("Juci", 25);
        laci = new Person("Laci", 28);
        barbi = new Person("Barbi", 32);
        moni = new Person("Móni", 45);
    }

    @Test
    void testSaveThenFind() {
        repository.savePerson(barbi);
        Long id = barbi.getId();
        Person other = repository.findPersonById(id);
        assertThat(other.getName()).isEqualTo("Barbi");
    }

}
package person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChildRepositoryTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    ChildRepository repository = new ChildRepository(factory);

    Child lacigyerek;
    Child liligyerek;
    Child belagyerek;
    Child tomigyerek;
    Child gabigyerek;

    @BeforeEach
    void init() {
        lacigyerek = new Child("Lacika", 2016);
        liligyerek = new Child("Lilike",2017);
        belagyerek = new Child("Bélus",2014);
        tomigyerek = new Child("Tomika",2012);
        gabigyerek = new Child("Gabesz",2015);
    }

    @Test
    void testSaveThenFind() {
        repository.saveChild(liligyerek);
        Long id = liligyerek.getId();
        Child other = repository.findChildById(id);
        assertThat(other.getName()).isEqualTo("Lilike");
    }

}
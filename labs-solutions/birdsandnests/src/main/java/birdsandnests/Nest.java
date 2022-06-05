package birdsandnests;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="nests")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Nest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="nest_id")
    private Long id;

    @Column(name="number_of_eggs")
    private int numberOfEggs;

    @OneToMany(mappedBy = "nest")
    private List<Bird> birds = new ArrayList<>();


    public Nest(int numberOfEggs) {
        this.numberOfEggs = numberOfEggs;
    }

    public Nest() {
    }

    public void addBird(Bird bird) {
        birds.add(bird);
        bird.setNest(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfEggs() {
        return numberOfEggs;
    }

    public void setNumberOfEggs(int numberOfEggs) {
        this.numberOfEggs = numberOfEggs;
    }

    public List<Bird> getBirds() {
        return birds;
    }

    public void setBirds(List<Bird> birds) {
        this.birds = birds;
    }
}

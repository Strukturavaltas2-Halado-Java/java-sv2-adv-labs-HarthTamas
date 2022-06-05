package birdsandnests;

import javax.persistence.Entity;

@Entity
public class RoundNest extends Nest{

    private int diameter;

    public RoundNest(int numberOfEggs, int diameter) {
        super(numberOfEggs);
        this.diameter = diameter;
    }

    public RoundNest() {

    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }


}

package usedcars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class KilometerState {

    private int kilometer;

    @Column(name="date_of_reading")
    private LocalDate dateOfReading;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KilometerState that = (KilometerState) o;
        return Objects.equals(dateOfReading, that.dateOfReading);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfReading);
    }
}

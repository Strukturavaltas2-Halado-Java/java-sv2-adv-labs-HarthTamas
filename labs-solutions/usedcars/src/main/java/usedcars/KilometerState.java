package usedcars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KilometerState {

    private int kilometer;

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

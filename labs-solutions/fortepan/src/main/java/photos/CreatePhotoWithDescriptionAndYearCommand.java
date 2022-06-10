package photos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhotoWithDescriptionAndYearCommand {

    @NotBlank(message = "Description cannot be null or empty")
    private String description;

    @Min(1800)
    private int year;
}

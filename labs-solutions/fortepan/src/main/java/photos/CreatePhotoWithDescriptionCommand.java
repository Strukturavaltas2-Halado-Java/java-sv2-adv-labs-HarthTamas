package photos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhotoWithDescriptionCommand {

    @NotBlank(message = "Name cannot be null or empty")
    private String description;
}

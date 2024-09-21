package workshop.pathfinder.domain.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfileEditForm {
    @Size(min = 3)
    private String userName;
    @Min(0)
    @Max(100)
    private int age;
}

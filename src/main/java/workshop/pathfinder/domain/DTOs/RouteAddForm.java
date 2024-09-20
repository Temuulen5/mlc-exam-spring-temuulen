package workshop.pathfinder.domain.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import workshop.pathfinder.domain.entities.Category;

@Data
public class RouteAddForm {
    private Long authorId;
    @Size(min = 5)
    @NotNull
    private String name;
    @Size(min = 5)
    @NotNull
    private String description;
    @NotNull
    private String level;
    private String videoUrl;
    private Category category;

    @Override
    public String toString() {
        return "RouteAddForm{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", level='" + level + '\'' +
               ", videoUrl='" + videoUrl + '\'' +
               ", category=" + category +
               '}';
    }
}

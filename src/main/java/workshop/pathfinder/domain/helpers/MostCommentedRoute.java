package workshop.pathfinder.domain.helpers;

import lombok.Data;
import workshop.pathfinder.domain.entities.Category;
import workshop.pathfinder.domain.entities.User;
import workshop.pathfinder.domain.enums.Level;

import java.util.List;

@Data
public class MostCommentedRoute {
    private Long id;
    private String gpxCoordinates;
    private Level level;
    private String name;
    private String description;
    private User author;
    private List<Category> category;
    private String videoUrl;
}

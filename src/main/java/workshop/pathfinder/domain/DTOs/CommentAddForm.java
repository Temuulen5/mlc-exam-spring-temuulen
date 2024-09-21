package workshop.pathfinder.domain.DTOs;

import jakarta.validation.constraints.Size;
import lombok.Data;
import workshop.pathfinder.domain.entities.Message;

import java.util.Date;

@Data
public class CommentAddForm {
    @Size(min = 10)
    private String textContent;
    private Date created;
    private Long route_id;
    private Long author_id;
}

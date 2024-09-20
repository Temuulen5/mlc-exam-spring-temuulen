package workshop.pathfinder.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean approved;

    private Date created;

    private String textContent;

    @ManyToOne
    private User author;

    @ManyToOne
    private Route route;
}

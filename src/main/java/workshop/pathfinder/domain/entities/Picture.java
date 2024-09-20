package workshop.pathfinder.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Lob
    private String url;
    @ManyToOne
    private User author;
    @ManyToOne
    private Route route;
}

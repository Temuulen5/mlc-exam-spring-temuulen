package workshop.pathfinder.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import workshop.pathfinder.domain.enums.Level;

import java.util.List;

@Entity
@Data
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100000)
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private Level level;

    private String name;

    @Column(length = 100000)
    private String description;

    @ManyToOne
    private User author;

    @ManyToMany
    @JoinTable(name = "routes_categories")
    private List<Category> category;

    private String videoUrl;
}

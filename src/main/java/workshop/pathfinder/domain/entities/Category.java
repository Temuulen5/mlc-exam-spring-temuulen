package workshop.pathfinder.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import workshop.pathfinder.domain.enums.CategoryName;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CategoryName name;
    @Lob
    private String description;
}

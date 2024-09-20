package workshop.pathfinder.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import workshop.pathfinder.domain.enums.Role;

@Data
@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

}

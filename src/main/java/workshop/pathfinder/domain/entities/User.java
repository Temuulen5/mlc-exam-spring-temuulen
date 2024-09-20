package workshop.pathfinder.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import workshop.pathfinder.domain.enums.Level;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age;
    @Column(unique = true, nullable = false)
    private String username;
    private String fullName;
    private String password;
    private String email;
    @ManyToMany()
    @JoinTable(name = "users_roles")
    private List<UserRole> roles;
    @Enumerated(EnumType.STRING)
    private Level level;

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", age=" + age +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", email='" + email + '\'' +
               ", roles=" + roles +
               ", level=" + level +
               '}';
    }
}

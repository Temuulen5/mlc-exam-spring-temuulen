package workshop.pathfinder.domain.helpers;

import lombok.Data;
import workshop.pathfinder.domain.enums.Role;

@Data
public class LoggedUser {
    private Long id;
    private String fullName;
    private Role role;
    private int age;
    private String username;

    public void Logout() {
        this.role = null;
        this.id = null;
        this.fullName = null;
        this.age = 0;
        this.username = null;
    }

}

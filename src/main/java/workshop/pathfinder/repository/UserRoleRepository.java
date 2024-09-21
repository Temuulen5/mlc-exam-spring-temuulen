package workshop.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshop.pathfinder.domain.entities.UserRole;
import workshop.pathfinder.domain.enums.Role;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findUserRoleByRole(Role role);
}

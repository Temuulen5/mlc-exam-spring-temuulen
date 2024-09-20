package workshop.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshop.pathfinder.domain.entities.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findRouteById(Long id);
}

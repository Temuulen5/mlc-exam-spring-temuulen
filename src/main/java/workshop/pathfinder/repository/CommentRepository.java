package workshop.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshop.pathfinder.domain.entities.Comment;
import workshop.pathfinder.domain.entities.Route;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    int countByRoute(Route route);
}

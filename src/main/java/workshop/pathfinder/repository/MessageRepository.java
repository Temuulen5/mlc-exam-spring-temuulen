package workshop.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshop.pathfinder.domain.entities.Message;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}

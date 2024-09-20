package workshop.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshop.pathfinder.domain.entities.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
}

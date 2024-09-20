package workshop.pathfinder.service;

import org.springframework.stereotype.Service;
import workshop.pathfinder.repository.PictureRepository;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }


}

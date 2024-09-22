package workshop.pathfinder.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import workshop.pathfinder.domain.DTOs.CommentAddForm;
import workshop.pathfinder.domain.entities.Comment;
import workshop.pathfinder.domain.helpers.LoggedUser;
import workshop.pathfinder.domain.helpers.MostCommentedRoute;
import workshop.pathfinder.domain.helpers.IdRouteDetail;
import workshop.pathfinder.repository.CommentRepository;
import workshop.pathfinder.repository.RouteRepository;
import workshop.pathfinder.repository.UserRepository;

import java.util.Date;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MostCommentedRoute mostCommentedRoute;
    private final LoggedUser loggedUser;
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final IdRouteDetail idRouteDetail;

    public CommentService(CommentRepository commentRepository,
                          MostCommentedRoute mostCommentedRoute,
                          LoggedUser loggedUser,
                          ModelMapper modelMapper,
                          RouteRepository routeRepository,
                          UserRepository userRepository,
                          IdRouteDetail idRouteDetail) {
        this.commentRepository = commentRepository;
        this.mostCommentedRoute = mostCommentedRoute;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.idRouteDetail = idRouteDetail;
    }

    public void AddComment(CommentAddForm commentAddForm) {
        Comment comment = new Comment();
        comment.setRoute(this.routeRepository.findRouteById(this.idRouteDetail.getId()));
        comment.setAuthor(this.userRepository.findUsersById(this.loggedUser.getId()));
        comment.setCreated(new Date());
        comment.setTextContent(commentAddForm.getTextContent());
        this.commentRepository.save(comment);
    }
}

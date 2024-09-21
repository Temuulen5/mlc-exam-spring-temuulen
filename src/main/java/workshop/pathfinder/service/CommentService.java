package workshop.pathfinder.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import workshop.pathfinder.domain.DTOs.CommentAddForm;
import workshop.pathfinder.domain.entities.Comment;
import workshop.pathfinder.domain.entities.Message;
import workshop.pathfinder.domain.helpers.LoggedUser;
import workshop.pathfinder.domain.helpers.MostCommentedRoute;
import workshop.pathfinder.repository.CommentRepository;
import workshop.pathfinder.repository.MessageRepository;
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

    public CommentService(CommentRepository commentRepository,
                          MostCommentedRoute mostCommentedRoute,
                          LoggedUser loggedUser,
                          ModelMapper modelMapper,
                          RouteRepository routeRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.mostCommentedRoute = mostCommentedRoute;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
    }

    public void AddComment(CommentAddForm commentAddForm) {
        Comment comment = new Comment();
        comment.setRoute(this.routeRepository.findRouteById(this.mostCommentedRoute.getId()));
        comment.setAuthor(this.userRepository.findUsersById(this.loggedUser.getId()));
        comment.setCreated(new Date());
        comment.setTextContent(commentAddForm.getTextContent());
        this.commentRepository.save(comment);
        System.err.printf("Comment with id %s added\n", comment.getId());
    }
}

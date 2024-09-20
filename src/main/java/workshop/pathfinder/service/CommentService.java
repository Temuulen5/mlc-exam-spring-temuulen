package workshop.pathfinder.service;

import org.springframework.stereotype.Service;
import workshop.pathfinder.domain.entities.Comment;
import workshop.pathfinder.domain.entities.Message;
import workshop.pathfinder.domain.helpers.MostCommentedRoute;
import workshop.pathfinder.repository.CommentRepository;
import workshop.pathfinder.repository.MessageRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MostCommentedRoute mostCommentedRoute;
    private final MessageRepository messageRepository;

    public CommentService(CommentRepository commentRepository, MostCommentedRoute mostCommentedRoute, MessageRepository messageRepository) {
        this.commentRepository = commentRepository;
        this.mostCommentedRoute = mostCommentedRoute;
        this.messageRepository = messageRepository;
    }

    public void AddComment(Comment comment) {

    }
}

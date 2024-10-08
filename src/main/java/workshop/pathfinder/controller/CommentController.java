package workshop.pathfinder.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workshop.pathfinder.domain.DTOs.CommentAddForm;
import workshop.pathfinder.domain.helpers.MostCommentedRoute;
import workshop.pathfinder.domain.helpers.IdRouteDetail;
import workshop.pathfinder.service.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final MostCommentedRoute mostCommentedRoute;
    private final IdRouteDetail idRouteDetail;

    public CommentController(CommentService commentService, MostCommentedRoute mostCommentedRoute, IdRouteDetail idRouteDetail) {
        this.commentService = commentService;
        this.mostCommentedRoute = mostCommentedRoute;
        this.idRouteDetail = idRouteDetail;
    }

    @PostMapping("/add")
    public String addComment(@Valid @ModelAttribute CommentAddForm comment,
                             RedirectAttributes redirectAttributes,
                             BindingResult bindingResult) {
        Long id = idRouteDetail.getId();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentAddForm", comment)
                    .addFlashAttribute("org.springframework.validation.BindingResult.commentAddForm", bindingResult);
            return "redirect:/routes/details/" + id.toString();
        }
        commentService.AddComment(comment);
        return "redirect:/routes/details/" + id.toString();
    }
}

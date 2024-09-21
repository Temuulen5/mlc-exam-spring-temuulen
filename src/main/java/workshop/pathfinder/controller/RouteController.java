package workshop.pathfinder.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import workshop.pathfinder.domain.DTOs.CommentAddForm;
import workshop.pathfinder.domain.DTOs.RouteAddForm;
import workshop.pathfinder.domain.helpers.LoggedUser;
import workshop.pathfinder.domain.helpers.MostCommentedRoute;
import workshop.pathfinder.repository.CommentRepository;
import workshop.pathfinder.repository.RouteRepository;
import workshop.pathfinder.service.RouteService;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteRepository routeRepository;
    private final RouteService routeService;
    private final LoggedUser loggedUser;
    private final MostCommentedRoute mostCommentedRoute;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    public RouteController(RouteRepository routeRepository,
                           RouteService routeService,
                           LoggedUser loggedUser,
                           MostCommentedRoute mostCommentedRoute,
                           ModelMapper modelMapper,
                           CommentRepository commentRepository) {
        this.routeRepository = routeRepository;
        this.routeService = routeService;
        this.loggedUser = loggedUser;
        this.mostCommentedRoute = mostCommentedRoute;
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
    }

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("routes", routeRepository.findAll());
        return "routes";
    }

    @GetMapping("pedestrian")
    public String getPedestrian(Model model) {
        return "pedestrian";
    }

    @GetMapping("bicycle")
    public String getBicycle(Model model) {
        return "bicycle";
    }

    @GetMapping("motorcycle")
    public String getMotorcycle(Model model) {
        return "motorcycle";
    }

    @GetMapping("car")
    public String getCar(Model model) {
        return "car";
    }

    @GetMapping("add")
    public String getAdd(Model model) {
        model.addAttribute("RouteAddForm", new RouteAddForm());
        return "add-route";
    }

    @PostMapping("add")
    public String addRoute(@ModelAttribute RouteAddForm routeAddForm) {
        routeAddForm.setAuthorId(loggedUser.getId());
        routeService.AddRoute(routeAddForm);
        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String getRouteDetail(Model model, @PathVariable("id") Long id) {
        routeService.FindMostCommentedRoute();
        if (mostCommentedRoute.getId().equals(id)) {
            model.addAttribute("mostCommentedRoute", this.mostCommentedRoute);
            model.addAttribute("commentAddForm", new CommentAddForm());
            model.addAttribute("allComments",
                    commentRepository.findAllByRoute(routeRepository.findRouteById(mostCommentedRoute.getId())));
        }
        return "route-details";
    }

}

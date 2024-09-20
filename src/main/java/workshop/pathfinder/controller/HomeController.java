package workshop.pathfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import workshop.pathfinder.domain.helpers.LoggedUser;
import workshop.pathfinder.domain.helpers.MostCommentedRoute;
import workshop.pathfinder.repository.UserRepository;
import workshop.pathfinder.repository.UserRoleRepository;
import workshop.pathfinder.service.RouteService;
import workshop.pathfinder.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final LoggedUser loggedUser;
    private final MostCommentedRoute mostCommentedRoute;
    private final RouteService routeService;

    public HomeController(UserService userService,
                          UserRepository userRepository,
                          UserRoleRepository userRoleRepository,
                          LoggedUser loggedUser,
                          MostCommentedRoute mostCommentedRoute,
                          RouteService routeService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.loggedUser = loggedUser;
        this.mostCommentedRoute = mostCommentedRoute;
        this.routeService = routeService;
    }

    @GetMapping()
    public String index(Model model) {
        this.routeService.FindMostCommentedRoute();
        model.addAttribute("mostCommentedRoute", this.mostCommentedRoute);

        System.out.println(this.mostCommentedRoute);
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @ModelAttribute("LoggedUser")
    public LoggedUser getLoggedUser() {
        return new LoggedUser();
    }

}

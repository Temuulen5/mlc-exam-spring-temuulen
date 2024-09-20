package workshop.pathfinder.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import workshop.pathfinder.domain.DTOs.RouteAddForm;
import workshop.pathfinder.domain.helpers.LoggedUser;
import workshop.pathfinder.domain.helpers.MostCommentedRoute;
import workshop.pathfinder.domain.entities.Route;
import workshop.pathfinder.repository.CommentRepository;
import workshop.pathfinder.repository.RouteRepository;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final CommentRepository commentRepository;
    private final MostCommentedRoute mostCommentedRoute;

    public RouteService(RouteRepository routeRepository,
                        ModelMapper modelMapper,
                        LoggedUser loggedUser,
                        CommentRepository commentRepository,
                        MostCommentedRoute mostCommentedRoute) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.commentRepository = commentRepository;
        this.mostCommentedRoute = mostCommentedRoute;
    }

    public void AddRoute(RouteAddForm routeAddForm) {
        routeAddForm.setAuthorId(loggedUser.getId());
        Route route = modelMapper.map(routeAddForm, Route.class);
        routeRepository.save(route);
    }

    public void FindMostCommentedRoute() {
        Route route = new Route();
        int maxValue = Integer.MIN_VALUE;
        for (Route r : routeRepository.findAll()) {
            if (commentRepository.countByRoute(r) > maxValue) {
                maxValue = commentRepository.countByRoute(r);
                route = r;
            }
        }
        modelMapper.map(route, mostCommentedRoute);
    }
}

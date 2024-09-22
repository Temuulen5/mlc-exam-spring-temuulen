package workshop.pathfinder.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import workshop.pathfinder.domain.helpers.LoggedUser;
import workshop.pathfinder.domain.helpers.MostCommentedRoute;
import workshop.pathfinder.service.RouteService;

@Component
@EnableAspectJAutoProxy
@Aspect
public class TrackBeans {

    private final LoggedUser loggedUser;
    private final MostCommentedRoute mostCommentedRoute;
    private final RouteService routeService;

    public TrackBeans(LoggedUser loggedUser,MostCommentedRoute mostCommentedRoute,RouteService routeService) {
        this.loggedUser = loggedUser;
        this.mostCommentedRoute = mostCommentedRoute;
        this.routeService = routeService;
    }
    @Pointcut("execution(* workshop.pathfinder.controller.HomeController.*(..))")
    public void trackController() {

    }
    @Before("trackController()")
    public void beforeIndex(JoinPoint joinPoint) {
        this.routeService.FindMostCommentedRoute();
        System.err.println(loggedUser.toString());
        System.err.println(mostCommentedRoute.toString());
    }
}

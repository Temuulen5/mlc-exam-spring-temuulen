package workshop.pathfinder.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import workshop.pathfinder.domain.helpers.LoggedUser;
import workshop.pathfinder.domain.helpers.MostCommentedRoute;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public MostCommentedRoute mostCommentedRoute() {
        return new MostCommentedRoute();
    }

    @Bean
    @SessionScope
    public LoggedUser LoggedUser() {
        return new LoggedUser();
    }
}

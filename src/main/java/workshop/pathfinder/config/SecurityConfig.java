package workshop.pathfinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import workshop.pathfinder.service.UserService;

import java.security.Security;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin.loginPage("/users/login")
                                .defaultSuccessUrl("/", true)
                                .permitAll()
                ).logout(logout ->
                        logout.logoutUrl("/users/logout")
                                .logoutSuccessUrl("/users/login")
                                .permitAll());
        return http.build();
    }
}

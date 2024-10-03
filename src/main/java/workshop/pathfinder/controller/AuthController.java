package workshop.pathfinder.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workshop.pathfinder.domain.DTOs.ProfileEditForm;
import workshop.pathfinder.domain.helpers.LoggedUser;
import workshop.pathfinder.domain.DTOs.UserLoginForm;
import workshop.pathfinder.domain.DTOs.UserRegisterForm;
import workshop.pathfinder.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class AuthController {
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(LoggedUser loggedUser, UserService userService, AuthenticationManager authenticationManager) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/profile/{id}")
    public String getProfile(Model model, @PathVariable("id") Long id) {
        if (id.equals(this.loggedUser.getId())) {
            model.addAttribute("LoggedUser", this.loggedUser);
            return "profile";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        this.loggedUser.Logout();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model, Model error) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterForm());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserRegisterForm userRegisterForm,
                           RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterForm", userRegisterForm)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterForm", bindingResult);
            return "redirect:/users/register";
        }
        this.userService.registerUser(userRegisterForm);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("UserLoginForm", new UserLoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute UserLoginForm userLoginForm, RedirectAttributes redirectAttributes,
                        BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginForm", userLoginForm)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginForm", bindingResult);
            return "redirect:/users/login";
        }
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(userLoginForm.getUserName(), userLoginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        this.userService.LoginUser(userLoginForm);
        return "redirect:/";
    }

    @GetMapping("/profile/edit/{id}")
    public String editProfile(Model model, @PathVariable("id") Long id) {
        id = loggedUser.getId();
        model.addAttribute("profileEditForm", new ProfileEditForm());
        return "edit-profile";
    }

    @PostMapping("/profile/edit/{id}")
    public String editProfileData(@ModelAttribute ProfileEditForm profileEditForm,
                                  RedirectAttributes redirectAttributes,
                                  BindingResult bindingResult, @PathVariable String id) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("profileEditForm", profileEditForm)
                    .addFlashAttribute("org.springframework.validation.BindingResult.profileEditForm", bindingResult);
        }
        userService.EditProfile(profileEditForm);
        return "redirect:/users/profile/" + loggedUser.getId();
    }
}
